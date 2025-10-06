package za.co.uride.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.uride.userservice.enums.EUserType;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto implements Serializable {
    private Long id;
    private String name;
    private boolean status;
    @NotBlank(message = "username is mandatory")
    private String username;
    private EUserType userType;
    private boolean verified;
    private boolean acceptTermsAndConditions;
    private boolean locked;
}
