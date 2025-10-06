package za.co.uride.userservice.dto;

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
public class UserDeviceDto implements Serializable {
    private  Long id;
    private  boolean status;
    private  String deviceKey;
    private  String deviceName;
    private  UserDto user;
}
