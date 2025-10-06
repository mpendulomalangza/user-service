package za.co.uride.userservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RoleEntitlementDto implements Serializable {
    private Long id;
    private boolean status;
    @NotNull(message = "role must not blank")
    private RoleDto role;
    @NotNull(message = "entitlement must not blank")
    private EntitlementDto entitlement;
}
