package za.co.uride.userservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.uride.userservice.enums.EContactType;
import za.co.uride.userservice.enums.ESystem;

import java.io.Serializable;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
public class RegisterDto implements Serializable {
    private String deviceKey;
    private String deviceName;
    private EContactType contactType;
    private String contact;
    private String fcmToken;
    private String role;
    @NotNull
    private ESystem system;
}
