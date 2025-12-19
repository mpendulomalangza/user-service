package za.co.uride.userservice.service;

import org.springframework.web.multipart.MultipartFile;
import za.co.uride.userservice.dto.PreSignedAvatarDto;
import za.co.uride.userservice.dto.PreSignedAvatarListItemDto;

import java.util.List;

public interface UserAvatarService {
    void save(MultipartFile multipartFile);

    PreSignedAvatarDto findAll();

    PreSignedAvatarDto findAll(long userId);

    List<PreSignedAvatarListItemDto> findAll(List<Long> users);
}
