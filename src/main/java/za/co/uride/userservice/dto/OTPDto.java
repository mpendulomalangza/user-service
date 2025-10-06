package za.co.uride.userservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.uride.userservice.enums.EVerificationType;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class OTPDto implements Serializable {
    private Long id;
    private boolean status;
    @NotBlank(message = "opt must not blank")
    @Min(value = 4, message = "opt should be a 4 digit number")
    private transient String otp;
    @NotBlank(message = "user is mandatory")
    private UserDto user;
    private LocalDateTime expiryDate;
    private EVerificationType type;
}
