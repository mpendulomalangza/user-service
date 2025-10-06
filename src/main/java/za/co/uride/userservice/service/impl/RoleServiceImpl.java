package za.co.uride.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.uride.userservice.domain.entity.Role;
import za.co.uride.userservice.domain.repository.RoleRepository;
import za.co.uride.userservice.dto.EntitlementDto;
import za.co.uride.userservice.dto.ListData;
import za.co.uride.userservice.dto.RoleAddUpdateDto;
import za.co.uride.userservice.dto.RoleDetailDto;
import za.co.uride.userservice.dto.RoleDto;
import za.co.uride.userservice.dto.RoleEntitlementDto;
import za.co.uride.userservice.dto.RoleListFilterDto;
import za.co.uride.userservice.enums.ESystem;
import za.co.uride.userservice.exception.FindException;
import za.co.uride.userservice.exception.UpdateException;
import za.co.uride.userservice.service.EntitlementService;
import za.co.uride.userservice.service.RoleEntitlementService;
import za.co.uride.userservice.service.RoleService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;
    private final EntitlementService entitlementService;
    private final RoleEntitlementService roleEntitlementService;
    private final ModelMapper modelMapper;

    @Transactional()
    @Override
    public RoleAddUpdateDto save(RoleAddUpdateDto roleAddUpdateDto) {
        try {
            Optional<Role> optional = repository.findByRoleNameAndSystemAndStatus(roleAddUpdateDto.getRoleName(), roleAddUpdateDto.getSystem(), true);
            if (optional.isPresent() && optional.get().getId() != roleAddUpdateDto.getId()) {
                throw new RuntimeException(String.format("Role name [%s] already exists in system[%s]", roleAddUpdateDto.getRoleName(), roleAddUpdateDto.getSystem().getValue()));
            }
            RoleDto roleDto = RoleDto.builder().id(roleAddUpdateDto.getId()).roleName(roleAddUpdateDto.getRoleName()).status(true).system(roleAddUpdateDto.getSystem()).build();
            Role role = modelMapper.map(roleDto, Role.class);
            role.setSystem(roleAddUpdateDto.getSystem());
            role.setLastModifiedOn(LocalDateTime.now());
            repository.save(role);
            roleDto = modelMapper.map(role, RoleDto.class);
            for (EntitlementDto entitlementDto : roleAddUpdateDto.getEntitlements()) {
                roleEntitlementService.save(RoleEntitlementDto.builder().entitlement(findEntitlement(entitlementDto.getName()))
                        .role(roleDto).build());
            }
            return modelMapper.map(role, RoleAddUpdateDto.class);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new UpdateException(ex.getMessage());
        }
    }

    private Role find(String role, ESystem userSystem) {
        Optional<Role> optionalRole = repository.findByRoleNameAndSystemAndStatus(role, userSystem, true);
        if (optionalRole.isEmpty()) {
            throw new FindException(String.format("Role [%s] does not exist in system[%s]", role, userSystem.getValue()));
        }
        return optionalRole.get();
    }

    @Override
    public RoleDetailDto findRoleDetail(String roleName, ESystem userSystem) {
        Role role = find(roleName, userSystem);
        RoleDetailDto roleDetailDto = modelMapper.map(role, RoleDetailDto.class);
        List<EntitlementDto> entitlements = role.getEntitlements().stream().map(roleEntitlement -> modelMapper.map(roleEntitlement.getEntitlement(), EntitlementDto.class)).collect(Collectors.toList());
        roleDetailDto.setEntitlements(entitlements);
        return roleDetailDto;
    }

    private EntitlementDto findEntitlement(String name) throws Exception {
        EntitlementDto entitlementDto;
        try {
            entitlementDto = entitlementService.findByNameAndStatus(name, true);
        } catch (FindException ex) {
            entitlementDto = entitlementService.save(EntitlementDto.builder().status(true).name(name).build());
        }
        return entitlementDto;
    }

    @Override
    public RoleDto findRole(String roleName, ESystem userSystem) {
        Role role = find(roleName, userSystem);
        return modelMapper.map(role, RoleDto.class);
    }

    @Override
    public ListData<RoleDto> findAll(final RoleListFilterDto filter) {

        Pageable pageable = PageRequest.of(filter.getPageNumber(), filter.getTotalPerPage());
        String filterString = StringUtils.defaultIfEmpty(filter.getFilter(), "");
        Page<Role> rolePage = repository.findAllByRoleNameContainsAndSystemAndStatus(filterString, filter.getSystem(), filter.isStatus(), pageable);
        return new ListData<>(rolePage.getContent().stream().map(role -> modelMapper.map(role, RoleDto.class)).collect(Collectors.toList()), rolePage.getTotalPages());
    }

}
