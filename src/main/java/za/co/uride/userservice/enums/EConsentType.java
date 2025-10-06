package za.co.uride.userservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EConsentType {
    MARKETING("marketing"),
    PROMOTION("promotion");
    private final String code;
}
