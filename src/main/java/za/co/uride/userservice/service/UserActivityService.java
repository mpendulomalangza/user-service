package za.co.uride.userservice.service;

import za.co.uride.userservice.dto.UserActivityDto;
import za.co.uride.userservice.dto.UserDto;
import za.co.uride.userservice.enums.EActivityType;

import java.time.LocalDateTime;

public interface UserActivityService {
    void create(UserActivityDto userActivityDto);

    long getActivityCount(UserDto userDto, EActivityType EActivityType, LocalDateTime localDateTime);
}
