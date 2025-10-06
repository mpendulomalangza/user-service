package za.co.uride.userservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.uride.userservice.enums.EContactType;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FindContactDto {
    @Positive
    private long id;
    @NotNull
    private EContactType eContactType;
}
