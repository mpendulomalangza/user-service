package za.co.uride.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import za.co.uride.userservice.domain.entity.User;
import za.co.uride.userservice.domain.entity.UserDevice;
import za.co.uride.userservice.domain.repository.UserDeviceRepository;
import za.co.uride.userservice.dto.UserDeviceDto;
import za.co.uride.userservice.dto.UserDto;
import za.co.uride.userservice.exception.FindException;
import za.co.uride.userservice.service.UserDeviceService;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserDeviceServiceImpl implements UserDeviceService {
    private final ModelMapper modelMapper;
    private final UserDeviceRepository repository;

    @Override
    public UserDeviceDto saveUserDevice(final UserDeviceDto dto) {
        UserDevice userDevice = modelMapper.map(dto, UserDevice.class);
        userDevice.setStatus(true);
        //check if user has a device already and update if user has one already
        try {
            UserDeviceDto existingUserDevice = findByUser(dto.getUser(), true);
            userDevice.setId(existingUserDevice.getId());
        } catch (FindException findException) {
            //Do nothing
            log.debug(findException.getMessage());
        }
        repository.save(userDevice);
        return modelMapper.map(userDevice, UserDeviceDto.class);
    }

    @Override
    public UserDeviceDto findByUser(UserDto userDto, boolean status) {
        User user = modelMapper.map(userDto, User.class);
        Optional<UserDevice> optional = repository.findByUserAndStatus(user, status);
        if (optional.isPresent()) {
            return modelMapper.map(optional.get(), UserDeviceDto.class);
        }
        throw new FindException(String.format("User with id [%s] does not have a device", userDto.getId()));
    }
}
