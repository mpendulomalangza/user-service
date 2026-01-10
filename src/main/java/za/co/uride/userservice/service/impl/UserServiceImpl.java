package za.co.uride.userservice.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import za.co.uride.userservice.domain.entity.User;
import za.co.uride.userservice.domain.repository.UserRepository;
import za.co.uride.userservice.dto.UserDto;
import za.co.uride.userservice.enums.EUserType;
import za.co.uride.userservice.exception.FindException;
import za.co.uride.userservice.service.UserService;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final ModelMapper modelMapper;

    private UserDto mapToDto(Optional<User> optional, String name, String value) {
        if (optional.isPresent()) {
            return modelMapper.map(optional.get(), UserDto.class);
        }
        throw new FindException(String.format("User with %s [%s] does not exist", name, value));
    }

    @Override
    public UserDto findByUsername(String username, EUserType userType) {
        Optional<User> optional = repository.findByUsernameAndUserTypeAndStatus(username, userType, true);
        return mapToDto(optional, "username", username);
    }

    @Override
    public UserDto findById(long id) {
        Optional<User> optional = repository.findById(id);
        return mapToDto(optional, "id", String.valueOf(id));
    }


    @Override
    public void lockUser(UserDto userDto) {
        changeUserLock(userDto, true);
    }

    void changeUserLock(UserDto userDto, boolean lockStatus) {
        UserDto existingUser = findById(userDto.getId());
        User user = modelMapper.map(existingUser, User.class);
        user.setLocked(lockStatus);
        repository.save(user);
    }

    @Override
    public void unlockUser(UserDto userDto) {
        changeUserLock(userDto, false);
    }


    @Override
    public UserDto save(@Valid UserDto userDto) {
        if (userDto.getId() != null && userDto.getId() == 0) {
            userDto.setId(null);
        }
        User user = modelMapper.map(userDto, User.class);
        user = repository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto findVerifiedUserById(long id) {
        User user = repository.findByIdAndVerified(id, true).orElseThrow(() -> new FindException(String.format("User with id %d does not exist", id)));
        return modelMapper.map(user, UserDto.class);
    }


}
