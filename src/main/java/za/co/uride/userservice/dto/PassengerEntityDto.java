package za.co.uride.userservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import za.co.uride.userservice.enums.EEntityType;

@Setter
@Getter
public class PassengerEntityDto extends EntityDto {
    @Builder
    public PassengerEntityDto(long id, String name, long userId) {
        super(id, EEntityType.PASSENGER, name, userId);
    }

    public PassengerEntityDto() {
        setEntityType(EEntityType.PASSENGER);
    }
}
