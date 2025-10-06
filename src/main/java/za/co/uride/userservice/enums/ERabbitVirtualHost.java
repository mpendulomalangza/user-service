package za.co.uride.userservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ERabbitVirtualHost {
    USER("user"),
    NOTIFICATION("notification");
    private final String virtualHost;
}
