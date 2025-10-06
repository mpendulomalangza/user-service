package za.co.uride.userservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.uride.userservice.enums.EConsentType;

import java.io.Serializable;

/**
 * DTO for {@link za.co.uride.userservice.domain.entity.UserConsent}
 */
@AllArgsConstructor
@Getter
@Builder
@Setter
@NoArgsConstructor
public class UserConsentDto implements Serializable {
    private Long id;
    @NotNull
    private EConsentType consentType;
    private UserDto user;
    private boolean consentGranted;
}