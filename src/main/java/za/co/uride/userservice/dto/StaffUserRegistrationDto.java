package za.co.uride.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.uride.userservice.enums.EIdentityType;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StaffUserRegistrationDto extends CompleteRegisterUserDto implements Serializable {
    @NotNull
    @Size(min = 10, max = 30)
    private String identityNumber;
    @NotNull
    private EIdentityType identityType;
    @NotBlank(message = "last is mandatory")
    private String lastName;
    @NotNull()
    @Past()
    private LocalDate dob;
}
