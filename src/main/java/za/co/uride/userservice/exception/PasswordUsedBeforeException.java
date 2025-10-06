package za.co.uride.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class PasswordUsedBeforeException extends RuntimeException {
    public PasswordUsedBeforeException(String message) {
        super(message);
    }
}
