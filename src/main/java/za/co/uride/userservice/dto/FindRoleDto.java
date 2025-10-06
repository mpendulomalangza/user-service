package za.co.uride.userservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.uride.userservice.enums.ESystem;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FindRoleDto {
    @NotNull(message = "system is a required field")
    private ESystem system;
    @NotNull(message = "role name is a required field")
    private String roleName;
}
