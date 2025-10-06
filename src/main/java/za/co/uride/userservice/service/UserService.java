package za.co.uride.userservice.service;

import za.co.uride.userservice.dto.UserDto;
import za.co.uride.userservice.enums.EUserType;

public interface UserService {

    UserDto findByUsername(String username, EUserType userType);

    UserDto findById(long id);

    void lockUser(UserDto userDto);

    void unlockUser(UserDto userDto);

    UserDto save(UserDto userDto);


}