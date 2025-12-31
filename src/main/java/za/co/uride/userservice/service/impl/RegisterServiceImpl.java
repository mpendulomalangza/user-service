package za.co.uride.userservice.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.uride.userservice.dto.ContactDto;
import za.co.uride.userservice.dto.DriverEntityDto;
import za.co.uride.userservice.dto.NotificationDto;
import za.co.uride.userservice.dto.OTPDto;
import za.co.uride.userservice.dto.PassengerEntityDto;
import za.co.uride.userservice.dto.RegisterDto;
import za.co.uride.userservice.dto.CompleteRegisterUserDto;
import za.co.uride.userservice.dto.RoleDto;
import za.co.uride.userservice.dto.UserConsentDto;
import za.co.uride.userservice.dto.UserDeviceDto;
import za.co.uride.userservice.dto.UserDto;
import za.co.uride.userservice.dto.UserPasswordDto;
import za.co.uride.userservice.dto.UserRoleDto;
import za.co.uride.userservice.enums.EConsentType;
import za.co.uride.userservice.enums.EContactType;
import za.co.uride.userservice.enums.ENotificationType;
import za.co.uride.userservice.enums.ERabbitVirtualHost;

import za.co.uride.userservice.enums.EUserType;
import za.co.uride.userservice.enums.EVerificationType;
import za.co.uride.userservice.exception.FindException;
import za.co.uride.userservice.exception.FoundException;
import za.co.uride.userservice.exception.UpdateException;

import za.co.uride.userservice.service.ContactService;
import za.co.uride.userservice.service.OTPService;
import za.co.uride.userservice.service.QueueMessageService;
import za.co.uride.userservice.service.RegisterService;
import za.co.uride.userservice.service.RoleService;
import za.co.uride.userservice.service.UserConsentService;
import za.co.uride.userservice.service.UserDeviceService;
import za.co.uride.userservice.service.UserPasswordService;
import za.co.uride.userservice.service.UserRoleService;
import za.co.uride.userservice.service.UserService;

import java.util.Collections;

@RequiredArgsConstructor
@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService {
    private final UserPasswordService userPasswordService;
    private final OTPService otpService;
    private final ContactService contactService;
    private final UserService userService;
    private final RoleService roleService;
    private final UserRoleService userRoleService;
    private final ModelMapper modelMapper;
    private final QueueMessageService queueMessageService;
    private final UserDeviceService userDeviceService;
    private final UserConsentService userConsentService;

    @Value("${queue.passenger}")
    private String passengerQueue;
    @Value("${queue.driver}")
    private String driverQueue;
    @Value("${queue.notification}")
    private String notificationQueue;
    @Value("${system.id}")
    private long systemId;

    @Transactional
    @Override
    public UserDto complete(CompleteRegisterUserDto completeRegisterUserDto) {
        try {
            UserDto userDto = userService.findById(completeRegisterUserDto.getId());
            userDto.setVerified(completeRegisterUserDto.isAcceptTermsAndConditions());
            userDto.setName(completeRegisterUserDto.getName());
            userDto.setAcceptTermsAndConditions(completeRegisterUserDto.isAcceptTermsAndConditions());

            userDto = userService.save(userDto);
            userPasswordService.create(UserPasswordDto.builder().user(userDto).password(completeRegisterUserDto.getPassword()).build());
            for (EConsentType consentType : completeRegisterUserDto.getConsentTypes()) {
                userConsentService.save(UserConsentDto.builder().consentType(consentType).consentGranted(true).user(userDto).build());
            }
            //Send the use to the e-hailing system
            boolean isPassenger = completeRegisterUserDto.getRole().equalsIgnoreCase("passenger");
            boolean isStaff = completeRegisterUserDto.getRole().equalsIgnoreCase("driver");
            if (isPassenger) {
                PassengerEntityDto passengerEntityDto = modelMapper.map(completeRegisterUserDto, PassengerEntityDto.class);
                passengerEntityDto.setUserId(userDto.getId());
                queueMessageService.sendMessage(passengerQueue, ERabbitVirtualHost.USER.getVirtualHost(), passengerEntityDto);

            } else if (isStaff) {
                DriverEntityDto driverEntityDto = modelMapper.map(completeRegisterUserDto, DriverEntityDto.class);
                driverEntityDto.setUserId(userDto.getId());
                queueMessageService.sendMessage(driverQueue, ERabbitVirtualHost.USER.getVirtualHost(), driverEntityDto);
            }
            return userDto;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new UpdateException(ex.getMessage());
        }
    }

    @Transactional
    @Override
    public UserDto register(@Valid RegisterDto registerDto) {
        try {
            UserDto userDto = modelMapper.map(registerDto, UserDto.class);
            userDto.setUserType(EUserType.PROFILE);
            userDto.setStatus(true);
            userDto.setAcceptTermsAndConditions(false);
            userDto.setUsername(registerDto.getContact());
            //Name is set on complete registration
            userDto.setName("");
            RoleDto roleDto = roleService.findRole(registerDto.getRole(), registerDto.getSystem());
            try {
                userService.findByUsername(userDto.getUsername(), EUserType.PROFILE);
                throw new FoundException(String.format("User with username [%s] already taken try a different one", userDto.getUsername()));

            } catch (FindException ex) {
                log.debug("user does not exist");
            }
            try {
                try {
                    //Check if email or cellphone already linked
                    if (registerDto.getContactType().equals(EContactType.CELLPHONE)) {
                        contactService.findByCellphone(registerDto.getContact(), 0);
                    } else {
                        contactService.findByEmailAddress(registerDto.getContact(), 0);
                    }
                    throw new Exception(String.format("User with %s address already taken try a different one", registerDto.getContact()));
                } catch (FindException ignore) {

                }
                userDto = userService.save(userDto);
                ContactDto.ContactDtoBuilder contactDtoBuilder = ContactDto.builder().user(userDto).fcmToken(registerDto.getFcmToken());
                if (registerDto.getContactType().equals(EContactType.EMAIL)) {
                    contactDtoBuilder.emailAddress(registerDto.getContact());
                } else {
                    contactDtoBuilder.cellphoneNumber(registerDto.getContact());
                }
                contactService.saveContact(contactDtoBuilder.build());
                OTPDto otpDto = otpService.createOTP(userDto, registerDto.getContactType().equals(EContactType.EMAIL) ? EVerificationType.EMAIL : EVerificationType.CELL);
                userDeviceService.saveUserDevice(UserDeviceDto.builder().user(userDto).deviceKey(registerDto.getDeviceKey()).deviceName(registerDto.getDeviceName()).build());
                userRoleService.save(UserRoleDto.builder().user(userDto).role(roleDto).build());
                queueMessageService.sendMessage(notificationQueue, ERabbitVirtualHost.NOTIFICATION.getVirtualHost(),
                        NotificationDto.builder().message(String.format("Your OTP is %s, it is valid for 30 minutes", otpDto.getOtp())).notificationType(ENotificationType.SMS).recipients(Collections.singletonList(userDto.getId())).senderId(systemId).build());

            } catch (FindException ignored) {
            }
            return userDto;
        } catch (Exception ex) {
            throw new UpdateException(ex.getMessage());
        }
    }
}
