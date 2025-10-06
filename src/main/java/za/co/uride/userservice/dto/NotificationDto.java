package za.co.uride.userservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.uride.userservice.enums.ENotificationType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class NotificationDto implements Serializable {
    private long senderId;
    @NotNull
    private ENotificationType notificationType;
    @NotEmpty
    private List<Long> recipients;
    @NotEmpty
    private String message;
    private String title;
    private Map<String, String> metadata = new HashMap<>();
    @NotNull
    private String sourceSystem;
}
