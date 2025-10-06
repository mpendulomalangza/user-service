package za.co.uride.userservice.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.uride.userservice.enums.EConsentType;
import za.co.uride.userservice.enums.ESystem;
import za.co.uride.userservice.enums.EUserType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
public class RegisterUserDto implements Serializable {
    private long id;
    @NotBlank(message = "name is mandatory")
    private String name;
    private boolean status;
    @NotBlank(message = "username is mandatory")
    private String username;
    @AssertTrue()
    private boolean acceptTermsAndConditions;
    private EUserType userType;
    private String deviceKey;
    private String deviceName;
    @NotNull
    @NotEmpty()
    private transient String password;
    @NotEmpty()
    private List<String> roles;
    @NotNull
    private ESystem system;
    @Builder.Default
    private List<EConsentType> consentTypes = new ArrayList<>();
    @NotBlank(message = "cellphone number is mandatory")
    private String cellphoneNumber;
    @NotBlank(message = "email address is mandatory")
    private String emailAddress;
    private String fcmToken;
}
