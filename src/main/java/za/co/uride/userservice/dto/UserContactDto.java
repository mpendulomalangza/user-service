package za.co.uride.userservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import za.co.uride.userservice.enums.EContactType;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class UserContactDto {
    @NotNull
    private EContactType contactType;
    @NotNull
    @NotEmpty
    private String contact;
}
