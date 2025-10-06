package za.co.uride.userservice.service;

import za.co.uride.userservice.dto.OTPDto;
import za.co.uride.userservice.dto.UserDto;
import za.co.uride.userservice.enums.EVerificationType;

public interface OTPService {
    OTPDto findByUser(UserDto userDto);

    OTPDto createOTP(UserDto userDto, EVerificationType verificationType);

    void validateOTP(OTPDto opt);

    void delete(UserDto userDto);


}
