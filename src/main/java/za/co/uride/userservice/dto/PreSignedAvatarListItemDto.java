package za.co.uride.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PreSignedAvatarListItemDto implements Serializable {
    private long userId;
    private String name;
    private String url;
}
