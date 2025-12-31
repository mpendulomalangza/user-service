package za.co.uride.userservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.uride.userservice.dto.ContactDetailsDto;
import za.co.uride.userservice.dto.FindContactDto;
import za.co.uride.userservice.dto.PostBody;
import za.co.uride.userservice.service.ContactDetailService;

@RequestMapping("contact-detail")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ContactDetailController {
    private final ContactDetailService contactDetailService;

    @PostMapping(name = "find", path = "/find/v1")
    public PostBody<ContactDetailsDto> register(@RequestBody @Valid FindContactDto findContactDto) {
        return new PostBody<>(contactDetailService.find(findContactDto));
    }
}
