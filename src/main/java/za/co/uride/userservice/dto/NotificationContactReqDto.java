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
@Getter
@Setter
@NoArgsConstructor
public class NotificationContactReqDto implements Serializable {
    private long notificationId;
    @Positive
    private long userId;
    @NotNull
    private EContactType contactType;
}
