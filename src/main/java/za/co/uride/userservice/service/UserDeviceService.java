package za.co.uride.userservice.service;

import za.co.uride.userservice.dto.UserDeviceDto;
import za.co.uride.userservice.dto.UserDto;

public interface UserDeviceService {
    /**
     * Method saves a device for a  user and updates it if the user already has a device
     * @Author mpendulo malangwane
     * @Company kryptonbtye
     **/
    UserDeviceDto saveUserDevice(UserDeviceDto dto);

    UserDeviceDto findByUser(UserDto userDto, boolean status);
}
