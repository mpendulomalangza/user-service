package za.co.uride.userservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public enum EActivityType {
    LOGIN("login"),
    OTP("opt"),
    PASSWORD_RESET("password-reset"),
    FAILED_LOGIN("failed login");
    private final String name;
}
