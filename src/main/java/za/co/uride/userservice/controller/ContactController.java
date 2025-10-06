package za.co.uride.userservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.uride.userservice.dto.ContactDto;
import za.co.uride.userservice.dto.PostBody;
import za.co.uride.userservice.dto.UserContactDto;
import za.co.uride.userservice.service.UserContactService;

@RestController
@RequestMapping("contact")
@RequiredArgsConstructor
public class ContactController {
    private final UserContactService userContactService;

    @PostMapping(name = "find", value = "find/v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ContactDto findContact(@RequestBody @Valid PostBody<UserContactDto> postBody) {
        return userContactService.getContact(postBody.getData());
    }

    @PostMapping(name = "update", value = "update/v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateContact(@RequestBody @Valid PostBody<UserContactDto> postBody) {
        userContactService.updateContact(postBody.getData());
    }

    @PostMapping(name = "available", value = "available/v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean isAvailable(@RequestBody @Valid PostBody<UserContactDto> postBody) {
        return userContactService.isAvailable(postBody.getData());
    }
}
