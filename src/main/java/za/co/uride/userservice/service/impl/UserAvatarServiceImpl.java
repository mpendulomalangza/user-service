package za.co.uride.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import za.co.uride.userservice.domain.entity.User;
import za.co.uride.userservice.domain.entity.UserAvatar;
import za.co.uride.userservice.domain.repository.UserAvatarRepository;
import za.co.uride.userservice.dto.PreSignedAvatarDto;

import za.co.uride.userservice.dto.PreSignedAvatarListItemDto;
import za.co.uride.userservice.dto.UserDto;
import za.co.uride.userservice.enums.EUserType;
import za.co.uride.userservice.exception.FindException;
import za.co.uride.userservice.exception.UpdateException;
import za.co.uride.userservice.security.JwtAuthenticationToken;
import za.co.uride.userservice.service.AmazonStorageService;
import za.co.uride.userservice.service.UserAvatarService;
import za.co.uride.userservice.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
            JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            UserDto userDto = userService.findByUsername((String) authentication.getPrincipal(), EUserType.PROFILE);
            Optional<UserAvatar> optionalUserAvatar = repository.findByUserId(userDto.getId(), LocalDateTime.now());
            if (optionalUserAvatar.isPresent()) {
                optionalUserAvatar.get().setEffTo(LocalDateTime.now());
                repository.save(optionalUserAvatar.get());
            }
            String key = "user-avatar/" + authentication.getUserId() + "_user_avatar" + Objects.requireNonNull(multipartFile.getOriginalFilename()).substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            amazonStorageService.upload(multipartFile, key);
            UserAvatar userAvatar = UserAvatar.builder().fileKey(key).user(modelMapper.map(userDto, User.class)).build();
            repository.save(userAvatar);
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    @Transactional
    @Override
    @PreAuthorize("hasAuthority('find-user-avatar')")
    public PreSignedAvatarDto findAll() {
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return findAll(authentication.getUserId());
    }

    @Transactional
    @Override
    @PreAuthorize("hasAnyAuthority('find-driver-avatar','find-passenger-avatar','find-user-avatar')")
    public PreSignedAvatarDto findAll(long userId) {
        Optional<UserAvatar> optionalUserAvatar = repository.findByUserId(userId, LocalDateTime.now());
        if (optionalUserAvatar.isEmpty()) {
            throw new FindException("No avatar found");
        }
        return PreSignedAvatarDto.builder().url(amazonStorageService.getFile(optionalUserAvatar.get().getFileKey())).build();
    }

    @Transactional
    @Override
    @PreAuthorize("hasAuthority('find-contact-avatar')")
    public List<PreSignedAvatarListItemDto> findAll(List<Long> users) {
        List<PreSignedAvatarListItemDto> avatars = new ArrayList<>();
        List<UserAvatar> userAvatarList = repository.findAll(users, LocalDateTime.now());
        for (UserAvatar userAvatar : userAvatarList) {
            avatars.add(PreSignedAvatarListItemDto.builder().url(amazonStorageService.getFile(userAvatar.getFileKey()))
                    .userId(userAvatar.getUser().getId()).name(userAvatar.getUser().getName()).build());
        }
        return avatars;
    }
}
