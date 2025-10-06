package za.co.uride.userservice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.uride.userservice.enums.ESystem;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RoleDto implements Serializable {
    private Long id;
    private boolean status;
    @NotBlank(message = "name must not blank")
    @Max(value = 100, message = "name should be a 4 digit number")
    private String roleName;
    private ESystem system;
}
