package za.co.uride.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RoleAddUpdateDto extends RoleDto {
    private List<EntitlementDto> entitlements;
}
