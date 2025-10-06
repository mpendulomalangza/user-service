package za.co.uride.userservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EVerificationType {
    CELL("cellphone"),
    EMAIL("email-address");
    private final String value;
}
