package za.co.uride.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NotificationContactRespDto {
    private long notificationId;
    private String contact;
    private String avatar;
    private String contactName;
}
