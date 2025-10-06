package za.co.uride.userservice.service;


import za.co.uride.userservice.dto.RegisterUserDto;
import za.co.uride.userservice.dto.UserDto;


public interface RegisterService {

    UserDto register(RegisterUserDto registerUserDto);

}
