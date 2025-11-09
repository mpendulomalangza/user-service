package za.co.uride.userservice.service;


import za.co.uride.userservice.dto.RegisterDto;
import za.co.uride.userservice.dto.CompleteRegisterUserDto;
import za.co.uride.userservice.dto.UserDto;


public interface RegisterService {

    UserDto complete(CompleteRegisterUserDto completeRegisterUserDto);

    UserDto register(RegisterDto registerDto);

}
