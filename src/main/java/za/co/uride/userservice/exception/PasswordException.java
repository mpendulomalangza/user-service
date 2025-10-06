package za.co.uride.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class PasswordException extends RuntimeException {
    public PasswordException(String message) {
        super(message);
    }
}
