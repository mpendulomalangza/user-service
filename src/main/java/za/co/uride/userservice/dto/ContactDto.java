package za.co.uride.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ContactDto implements Serializable {
    private Long id;
    private boolean status;
    @NotBlank(message = "cellphone number is mandatory")
    private String cellphoneNumber;
    @NotBlank(message = "email address is mandatory")
    private String emailAddress;
    @Builder.Default
    private boolean emailAddressVerified = false;
    @Builder.Default
    private boolean cellphoneNumberVerified = false;
    private String fcmToken;
    private UserDto user;

    @Override
    public String toString() {
        return String.format("contact{user-id:%d}", user.getId());
    }
}
