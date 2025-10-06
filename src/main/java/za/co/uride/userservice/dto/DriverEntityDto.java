package za.co.uride.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.uride.userservice.enums.EEntityType;
import za.co.uride.userservice.enums.EIdentityType;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class DriverEntityDto extends EntityDto {

    private EIdentityType identityType;
    @NotBlank(message = "name is mandatory")
    private String lastName;
    @NotNull
    private String identityNumber;

    @NotNull
    private LocalDate dob;

    @Builder
    public DriverEntityDto(long id, String identityNumber, EEntityType entityType, String name, long userId,
                           EIdentityType identityType, String lastName, LocalDate dob) {
        super(id, entityType, name, userId);
        this.identityType = identityType;
        this.identityNumber = identityNumber;
        this.lastName = lastName;
        this.dob = dob;
    }
}
