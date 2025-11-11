package za.co.uride.userservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.uride.userservice.dto.PostBody;
import za.co.uride.userservice.dto.RegisterDto;
import za.co.uride.userservice.dto.CompleteRegisterUserDto;
import za.co.uride.userservice.dto.CompleteStaffUserRegistrationDto;
import za.co.uride.userservice.dto.UserDto;
import za.co.uride.userservice.service.RegisterService;

@RequestMapping("user")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final RegisterService registerService;

    @PostMapping(name = "complete-registration", path = "/complete-register/v1")
    public PostBody<UserDto> completeRegister(@RequestBody @Valid PostBody<CompleteRegisterUserDto> postBody) {
        CompleteRegisterUserDto completeRegisterUserDto = postBody.getData();
        return new PostBody<>(registerService.complete(completeRegisterUserDto));
    }

    @PostMapping(name = "register", path = "/register/v1")
    public PostBody<UserDto> register(@RequestBody @Valid PostBody<RegisterDto> postBody) {
        return new PostBody<>(registerService.register(postBody.getData()));
    }

    @PostMapping(name = "complete-staff-registration", path = "/register-staff/v1")
    public PostBody<UserDto> registerStaff(@RequestBody @Valid PostBody<CompleteStaffUserRegistrationDto> postBody) {
        CompleteRegisterUserDto completeRegisterUserDto = postBody.getData();
        return new PostBody<>(registerService.complete(completeRegisterUserDto));
    }

}
