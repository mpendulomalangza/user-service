package za.co.uride.userservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EUserType {
    PROFILE("internal"),
    GOOGLE("gmail");
    private final String value;
}
