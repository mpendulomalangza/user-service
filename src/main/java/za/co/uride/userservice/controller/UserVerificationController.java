package za.co.uride.userservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.uride.userservice.dto.CompleteVerifyUserDto;
import za.co.uride.userservice.dto.PostBody;
import za.co.uride.userservice.dto.VerifyUserDto;
import za.co.uride.userservice.service.UserVerificationService;


@RequestMapping("user-verification")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserVerificationController {
    private final UserVerificationService userVerificationService;

    @PostMapping(name = "start-verification", path = "/start/v1")
    public void start(@Valid @RequestBody PostBody<VerifyUserDto> postBody) {
        userVerificationService.startVerification(postBody.getData());
    }

    @PostMapping(name = "complete-verification", path = "/complete/v1")
    public void complete(@Valid @RequestBody PostBody<CompleteVerifyUserDto> postBody) {
        userVerificationService.complete(postBody.getData());
    }

}
