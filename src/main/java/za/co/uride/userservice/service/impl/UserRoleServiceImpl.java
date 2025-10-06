package za.co.uride.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import za.co.uride.userservice.domain.entity.User;
import za.co.uride.userservice.domain.entity.UserRole;
import za.co.uride.userservice.domain.repository.UserRoleRepository;
import za.co.uride.userservice.dto.EntitlementDto;
import za.co.uride.userservice.dto.RoleDetailDto;
import za.co.uride.userservice.dto.UserDto;
import za.co.uride.userservice.dto.UserRoleDto;
import za.co.uride.userservice.enums.ESystem;
import za.co.uride.userservice.exception.UpdateException;
import za.co.uride.userservice.service.UserRoleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public UserRoleDto save(UserRoleDto userRoleDto) {
        UserRole userRole = modelMapper.map(userRoleDto, UserRole.class);
        Optional<UserRole> optional = repository.findByRoleAndUserAndStatus(userRole.getRole(), userRole.getUser(), true);
        if (optional.isPresent() && optional.get().getId().equals(userRole.getId())) {
            throw new UpdateException(String.format("User[%s] already has role[%s] assigned", userRoleDto.getUser().getId(),
                    userRoleDto.getRole().getId()));
        }
        userRole.setStatus(true);
        repository.save(userRole);
        return modelMapper.map(userRole, UserRoleDto.class);
    }

    @Override
    public List<RoleDetailDto> findAllByUser(final UserDto userDto, final ESystem system) {
        List<RoleDetailDto> roles = new ArrayList<>();
        List<UserRole> userRoles = repository.findAllByUserAndStatus(modelMapper.map(userDto, User.class), true);
        for (UserRole userRole : userRoles) {
            if (userRole.getRole().getSystem().equals(system)) {
                RoleDetailDto roleDetailDto = modelMapper.map(userRole.getRole(), RoleDetailDto.class);
                roleDetailDto.setEntitlements(userRole.getRole().getEntitlements().stream().map(roleEntitlement -> modelMapper.map(roleEntitlement.getEntitlement(), EntitlementDto.class)).collect(Collectors.toList()));
                roles.add(roleDetailDto);
            }
        }
        return roles;
    }

}
