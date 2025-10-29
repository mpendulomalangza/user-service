package za.co.uride.userservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.uride.userservice.enums.EContactType;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FindContactDto implements Serializable {
    @Positive
    private long id;
    @NotNull
    private EContactType eContactType;

    @Override
    public String toString() {
        return String.format("contact{user-id:%d}", id);
    }
}
