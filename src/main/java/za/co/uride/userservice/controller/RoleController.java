package za.co.uride.userservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.uride.userservice.dto.FindRoleDto;
import za.co.uride.userservice.dto.ListData;
import za.co.uride.userservice.dto.PostBody;
import za.co.uride.userservice.dto.RoleAddUpdateDto;
import za.co.uride.userservice.dto.RoleDetailDto;
import za.co.uride.userservice.dto.RoleDto;
import za.co.uride.userservice.dto.RoleListFilterDto;
import za.co.uride.userservice.service.RoleService;


@RequestMapping("role")
@RestController
@RequiredArgsConstructor
@Slf4j

public class RoleController {
    private final RoleService roleService;

    @Secured(value = "edit-role")
    @PostMapping(name = "save-role", path = "/save/v1", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@Valid @RequestBody PostBody<RoleAddUpdateDto> postBody) {
        roleService.save(postBody.getData());
    }

    @Secured(value = "find-role")
    @PostMapping(name = "find-role", path = "/find/v1", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PostBody<RoleDetailDto> find(@Valid @RequestBody PostBody<FindRoleDto> postBody) {
        return new PostBody<>(roleService.findRoleDetail(postBody.getData().getRoleName(), postBody.getData().getSystem()));
    }

    @Secured(value = "find-all-role")
    @PostMapping(name = "find-roles", path = "/find-all/v1", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PostBody<ListData<RoleDto>> findAll(@Valid @RequestBody PostBody<RoleListFilterDto> postBody) {
        return new PostBody<>(roleService.findAll(postBody.getData()));
    }

}
