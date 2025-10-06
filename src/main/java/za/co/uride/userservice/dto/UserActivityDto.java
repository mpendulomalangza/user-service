package za.co.uride.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.uride.userservice.enums.EActivityType;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserActivityDto implements Serializable {
    private Long id;
    private boolean status;
    private EActivityType EActivityType;
    private UserDto user;
    private LocalDateTime lastModifiedOn;
}
