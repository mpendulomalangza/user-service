package za.co.uride.userservice.service.impl;

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
import za.co.uride.userservice.dto.RegisterUserDto;
import za.co.uride.userservice.dto.RoleDto;
import za.co.uride.userservice.dto.UserConsentDto;
import za.co.uride.userservice.dto.UserDeviceDto;
import za.co.uride.userservice.dto.UserDto;
import za.co.uride.userservice.dto.UserPasswordDto;
import za.co.uride.userservice.dto.UserRoleDto;
import za.co.uride.userservice.enums.EConsentType;
import za.co.uride.userservice.enums.ENotificationType;
import za.co.uride.userservice.enums.ERabbitVirtualHost;
import za.co.uride.userservice.enums.ESystem;
import za.co.uride.userservice.enums.EVerificationType;
import za.co.uride.userservice.exception.FindException;
import za.co.uride.userservice.exception.FoundException;
import za.co.uride.userservice.exception.UpdateException;
import za.co.uride.userservice.queue.ContactConsumer;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private final ContactConsumer contactConsumer;
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
    public UserDto register(RegisterUserDto registerUserDto) {
        try {
            UserDto userDto = modelMapper.map(registerUserDto, UserDto.class);
            List<RoleDto> roles = new ArrayList<>();
            //Validate roles and add them role list
            for (String role : registerUserDto.getRoles()) {
                roles.add(roleService.findRole(role, registerUserDto.getSystem()));
            }
            try {
                userService.findByUsername(userDto.getUsername(), registerUserDto.getUserType());
                throw new FoundException(String.format("User with username [%s] already taken try a different one", userDto.getUsername()));

            } catch (FindException ex) {
                log.debug("user does not exist");
            }
            try {
                //Check if email or cellphone already linked
                if (contactService.findByCellphone(registerUserDto.getCellphoneNumber(), 0) != null || contactService.findByEmailAddress(registerUserDto.getEmailAddress(), 0) != null) {
                    throw new Exception("User with cellphone or email address already taken try a different one");
                }
            } catch (FindException ignored) {
            }

            userDto = userService.save(userDto);

            contactService.saveContact(ContactDto.builder().user(userDto).cellphoneNumber(registerUserDto.getCellphoneNumber()).emailAddress(registerUserDto.getEmailAddress()).fcmToken(registerUserDto.getFcmToken()).build());

            userPasswordService.create(UserPasswordDto.builder().user(userDto).password(registerUserDto.getPassword()).build());
            for (RoleDto roleDto : roles) {
                UserRoleDto userRoleDto = UserRoleDto.builder().role(roleDto).user(userDto).build();
                userRoleService.save(userRoleDto);
            }
            for (EConsentType consentType : registerUserDto.getConsentTypes()) {
                userConsentService.save(UserConsentDto.builder().consentType(consentType).consentGranted(true).user(userDto).build());
            }
            //Send the use to the e-hailing system
            if (registerUserDto.getSystem().equals(ESystem.URIDE)) {
                boolean isPassenger = registerUserDto.getRoles().stream().anyMatch(role -> role.equalsIgnoreCase("passenger"));
                boolean isStaff = registerUserDto.getRoles().stream().anyMatch(role -> role.equalsIgnoreCase("driver"));
                if (isPassenger) {
                    PassengerEntityDto passengerEntityDto = modelMapper.map(registerUserDto, PassengerEntityDto.class);
                    passengerEntityDto.setUserId(userDto.getId());
                    queueMessageService.sendMessage(passengerQueue, ERabbitVirtualHost.USER.getVirtualHost(), passengerEntityDto);

                } else if (isStaff) {
                    DriverEntityDto driverEntityDto = modelMapper.map(registerUserDto, DriverEntityDto.class);
                    driverEntityDto.setUserId(userDto.getId());
                    queueMessageService.sendMessage(driverQueue, ERabbitVirtualHost.USER.getVirtualHost(), driverEntityDto);
                }
            }
            OTPDto otpDto = otpService.createOTP(userDto, EVerificationType.CELL);
            userDeviceService.saveUserDevice(UserDeviceDto.builder().user(userDto).deviceKey(registerUserDto.getDeviceKey()).deviceName(registerUserDto.getDeviceName()).build());
            queueMessageService.sendMessage(notificationQueue, ERabbitVirtualHost.NOTIFICATION.getVirtualHost(),
                    NotificationDto.builder().message(String.format("Your OTP is %s, it is valid for 30 minutes", otpDto.getOtp())).notificationType(ENotificationType.SMS).recipients(Collections.singletonList(userDto.getId())).senderId(systemId).build());
            return userDto;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new UpdateException(ex.getMessage());
        }
    }
}
