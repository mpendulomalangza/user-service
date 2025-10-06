package za.co.uride.userservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.uride.userservice.dto.PostBody;
import za.co.uride.userservice.dto.RegisterUserDto;
import za.co.uride.userservice.dto.StaffUserRegistrationDto;
import za.co.uride.userservice.dto.UserDto;
import za.co.uride.userservice.service.RegisterService;

import java.security.NoSuchAlgorithmException;

@RequestMapping("user")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final RegisterService registerService;

    @PostMapping(name = "register", path = "/register/v1")
    public PostBody<UserDto> register(@RequestBody @Valid PostBody<RegisterUserDto> postBody) throws NoSuchAlgorithmException {
        RegisterUserDto registerUserDto = postBody.getData();
        return new PostBody<>(registerService.register(registerUserDto));
    }

    @PostMapping(name = "register-staff", path = "/register-staff/v1")
    public PostBody<UserDto> registerStaff(@RequestBody @Valid PostBody<StaffUserRegistrationDto> postBody) throws NoSuchAlgorithmException {
        RegisterUserDto registerUserDto = postBody.getData();
        return new PostBody<>(registerService.register(registerUserDto));
    }

}
