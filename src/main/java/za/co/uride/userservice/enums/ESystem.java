package za.co.uride.userservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ESystem {
    FARM("farm-management"),
    SCHOOL("school-management"),
    DELIVERY("food-delivery"),
    URIDE("uride");
    private final String value;
}
