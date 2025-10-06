package za.co.uride.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import za.co.uride.userservice.domain.entity.User;
import za.co.uride.userservice.domain.entity.UserActivity;
import za.co.uride.userservice.domain.repository.UserActivityRepository;
import za.co.uride.userservice.dto.UserActivityDto;
import za.co.uride.userservice.dto.UserDto;
import za.co.uride.userservice.enums.EActivityType;
import za.co.uride.userservice.service.UserActivityService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserActivityServiceImpl implements UserActivityService {
    private final ModelMapper modelMapper;
    private final UserActivityRepository repository;

    @Override
    public void create(UserActivityDto userActivityDto) {
        UserActivity userActivity = modelMapper.map(userActivityDto, UserActivity.class);
        repository.save(userActivity);
    }

    @Override
    public long getActivityCount(UserDto userDto, EActivityType EActivityType, LocalDateTime localDateTime) {
        return repository.countByActivityTypeAndUserAndLastModifiedOnIsBetween(EActivityType, modelMapper.map(userDto, User.class), localDateTime, LocalDateTime.now());
    }
}
