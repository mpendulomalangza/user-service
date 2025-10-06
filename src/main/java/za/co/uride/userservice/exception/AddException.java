package za.co.uride.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class AddException extends RuntimeException {
    public AddException(String message) {
        super(message);
    }
}
