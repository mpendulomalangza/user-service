package za.co.uride.userservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import za.co.uride.userservice.dto.FindDto;
import za.co.uride.userservice.dto.PreSignedAvatarDto;
import za.co.uride.userservice.service.UserAvatarService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "user-avatar")
public class UserAvatarController {
    private final UserAvatarService userAvatarService;

    @PostMapping(value = "/find/v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PreSignedAvatarDto find() {
        return userAvatarService.find();
    }

    @PostMapping(value = "/find-by-id/v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PreSignedAvatarDto findBy(@RequestBody @Valid FindDto findDto) {
        return userAvatarService.find(findDto.getId());
    }

    @PostMapping(value = "/upload/v1", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestParam("file") MultipartFile file) {
        userAvatarService.save(file);
    }

}
