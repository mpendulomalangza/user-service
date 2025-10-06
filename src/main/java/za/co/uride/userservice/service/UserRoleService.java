package za.co.uride.userservice.service;

import za.co.uride.userservice.dto.RoleDetailDto;
import za.co.uride.userservice.dto.UserDto;
import za.co.uride.userservice.dto.UserRoleDto;
import za.co.uride.userservice.enums.ESystem;

import java.util.List;

public interface UserRoleService {
    UserRoleDto save(UserRoleDto userRoleDto);

    List<RoleDetailDto> findAllByUser(UserDto userDto, ESystem system);
}
