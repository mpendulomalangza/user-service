package za.co.uride.userservice.service;

import za.co.uride.userservice.dto.ListData;
import za.co.uride.userservice.dto.RoleAddUpdateDto;
import za.co.uride.userservice.dto.RoleDetailDto;
import za.co.uride.userservice.dto.RoleDto;
import za.co.uride.userservice.dto.RoleListFilterDto;
import za.co.uride.userservice.enums.ESystem;

public interface RoleService {
    RoleAddUpdateDto save(RoleAddUpdateDto roleAddUpdateDto);

    RoleDetailDto findRoleDetail(String roleName, ESystem userSystem);

    RoleDto findRole(String roleName, ESystem userSystem);

    ListData<RoleDto> findAll(RoleListFilterDto roleListFilterDto);
}
