package za.co.uride.userservice.dto;

import jakarta.validation.constraints.AssertTrue;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.uride.userservice.enums.EConsentType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
public class CompleteRegisterUserDto implements Serializable {
    @Positive
    private long id;
    @NotBlank(message = "name is mandatory")
    private String name;
    @NotBlank(message = "role is mandatory")
    private String role;
    @AssertTrue()
    private boolean acceptTermsAndConditions;
    @NotNull
    @NotEmpty()
    private transient String password;
    @Builder.Default
    private List<EConsentType> consentTypes = new ArrayList<>();
}
