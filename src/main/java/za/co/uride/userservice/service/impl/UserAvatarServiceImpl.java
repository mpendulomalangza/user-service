package za.co.uride.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import za.co.uride.userservice.domain.entity.User;
import za.co.uride.userservice.domain.entity.UserAvatar;
import za.co.uride.userservice.domain.repository.UserAvatarRepository;
import za.co.uride.userservice.dto.PreSignedAvatarDto;
import za.co.uride.userservice.dto.UserDto;
import za.co.uride.userservice.enums.EUserType;
import za.co.uride.userservice.exception.FindException;
import za.co.uride.userservice.exception.UpdateException;
import za.co.uride.userservice.security.JwtAuthenticationToken;
import za.co.uride.userservice.service.AmazonStorageService;
import za.co.uride.userservice.service.UserAvatarService;
import za.co.uride.userservice.service.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAvatarServiceImpl implements UserAvatarService {

    private final UserAvatarRepository repository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final AmazonStorageService amazonStorageService;

    @Override
    @PreAuthorize("hasAuthority('edit-user-avatar')")
    public void save(MultipartFile multipartFile) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDto userDto = userService.findByUsername((String) authentication.getPrincipal(), EUserType.PROFILE);
            Optional<UserAvatar> optionalUserAvatar = repository.findByUserId(userDto.getId(), LocalDateTime.now());
            if (optionalUserAvatar.isPresent()) {
                optionalUserAvatar.get().setEffTo(LocalDateTime.now());
                repository.save(optionalUserAvatar.get());
            }
            String key = amazonStorageService.upload(multipartFile);
            UserAvatar userAvatar = UserAvatar.builder().fileKey(key).user(modelMapper.map(userDto, User.class)).build();
            repository.save(userAvatar);
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    @Transactional
    @Override
    @PreAuthorize("hasAuthority('find-user-avatar')")
    public PreSignedAvatarDto find() {
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return find(authentication.getUserId());
    }

    @Transactional
    @Override
    @PreAuthorize("hasAnyAuthority('find-driver-avatar','find-passenger-avatar','find-user-avatar')")
    public PreSignedAvatarDto find(long userId) {
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Optional<UserAvatar> userAvatarList = repository.findByUserId(authentication.getUserId(), LocalDateTime.now());
        if (userAvatarList.isEmpty()) {
            throw new FindException("No avatar found");
        }
        return PreSignedAvatarDto.builder().url(amazonStorageService.getFile(userAvatarList.get().getFileKey())).build();
    }
}
