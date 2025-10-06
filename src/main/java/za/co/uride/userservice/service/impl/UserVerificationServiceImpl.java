package za.co.uride.userservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import za.co.uride.userservice.dto.CompleteVerifyUserDto;
import za.co.uride.userservice.dto.ContactDto;
import za.co.uride.userservice.dto.NotificationDto;
import za.co.uride.userservice.dto.OTPDto;
import za.co.uride.userservice.dto.UserActivityDto;
import za.co.uride.userservice.dto.UserDto;
import za.co.uride.userservice.dto.VerifyUserDto;
import za.co.uride.userservice.enums.EActivityType;
import za.co.uride.userservice.enums.EContactType;
import za.co.uride.userservice.enums.ENotificationType;
import za.co.uride.userservice.enums.ERabbitVirtualHost;
import za.co.uride.userservice.enums.EVerificationType;
import za.co.uride.userservice.exception.OTPExceedLimit;
import za.co.uride.userservice.service.ContactService;
import za.co.uride.userservice.service.OTPService;
import za.co.uride.userservice.service.QueueMessageService;
import za.co.uride.userservice.service.UserActivityService;
import za.co.uride.userservice.service.UserService;
import za.co.uride.userservice.service.UserVerificationService;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserVerificationServiceImpl implements UserVerificationService {
    private final UserService userService;
    private final OTPService otpService;
    private final ContactService contactService;
    private final QueueMessageService queueMessageService;
    private final UserActivityService userActivityService;
    @Value("${queue.notification}")
    private String notificationQueue;
    @Value("${otp.duration}")
    private int otpDuration;
    @Value("${otp.limit}")
    private int otpLimit;
    @Value("${system.id}")
    private long systemId;

    /**
     * @param contact          contact
     * @param verificationType verification type
     * @return {@link UserDto}
     */
    private ContactDto getContact(String contact, EVerificationType verificationType) {
        ContactDto contactDto = null;
        if (verificationType.equals(EVerificationType.CELL)) {
            contactDto = contactService.findByCellphone(contact, 0);
        } else {
            contactDto = contactService.findByEmailAddress(contact, 0);
        }
        return contactDto;
    }

    @Transactional
    @Override
    public void startVerification(VerifyUserDto verifyUserDto) {
        ContactDto contactDto = getContact(verifyUserDto.getContact(), verifyUserDto.getVerificationType());
        UserDto userDto = contactDto.getUser();
        OTPDto otpDto = otpService.createOTP(userDto, verifyUserDto.getVerificationType());
        userActivityService.create(UserActivityDto.builder().EActivityType(EActivityType.OTP).user(userDto).status(true).lastModifiedOn(LocalDateTime.now()).build());
        //check if user has not reached one time pin request limit
        long total = userActivityService.getActivityCount(userDto, EActivityType.OTP, LocalDateTime.now().minusMinutes(otpDuration));
        if (total > otpLimit) {
            throw new OTPExceedLimit(String.format("You have reached max OTP limit please try again in %s minutes", otpDuration));
        }
        userDto.setVerified(false);
        userService.save(userDto);
        queueMessageService.sendMessage(notificationQueue, ERabbitVirtualHost.NOTIFICATION.getVirtualHost(),
                NotificationDto.builder().message(String.format("Your OTP is %s, it is valid for 30 minutes", otpDto.getOtp())).notificationType(ENotificationType.SMS).recipients(Collections.singletonList(userDto.getId())).senderId(systemId).build());
    }

    @Transactional
    @Override
    public void complete(CompleteVerifyUserDto completeUserVerificationDto) {
        ContactDto contactDto = getContact(completeUserVerificationDto.getContact(), completeUserVerificationDto.getVerificationType());
        UserDto userDto = contactDto.getUser();
        otpService.validateOTP(OTPDto.builder().user(userDto).type(completeUserVerificationDto.getVerificationType()).otp(completeUserVerificationDto.getOtp()).build());
        userDto.setVerified(true);
        userService.save(userDto);
        EContactType contactType = completeUserVerificationDto.getVerificationType().equals(EVerificationType.CELL) ? EContactType.CELLPHONE : EContactType.EMAIL;
        contactService.verifyContact(contactType, contactDto.getId());
    }
}
