package za.co.uride.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }
}
