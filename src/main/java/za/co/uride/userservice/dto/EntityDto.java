package za.co.uride.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.uride.userservice.enums.EEntityType;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EntityDto {
    private long id;
    private EEntityType entityType;
    private String name;
    private long userId;
}
