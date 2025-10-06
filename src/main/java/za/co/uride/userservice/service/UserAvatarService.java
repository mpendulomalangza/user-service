package za.co.uride.userservice.service;

import org.springframework.web.multipart.MultipartFile;
import za.co.uride.userservice.dto.PreSignedAvatarDto;

public interface UserAvatarService {
    void save(MultipartFile multipartFile);

    PreSignedAvatarDto find();

    PreSignedAvatarDto find(long userId);
}
