package za.co.uride.userservice.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import za.co.uride.userservice.enums.EVerificationType;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class CompleteVerifyUserDto {
    @NotNull
    private EVerificationType verificationType;
    @NotEmpty
    private String contact;
    @Digits(integer = 4, fraction = 0)
    private String otp;
}
