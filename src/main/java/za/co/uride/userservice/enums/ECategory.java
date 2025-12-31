package za.co.uride.userservice.enums;

import lombok.Getter;

/**
 * Category type
 */
@Getter
public enum ECategory {
    DIRECT("direct"),
    BOARD_CAST("broad-coast"),
    REQUEST("request");
    private final String code;

    ECategory(String code) {
        this.code = code;
    }
}
