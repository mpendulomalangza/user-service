package za.co.uride.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class FoundException extends RuntimeException {
    public FoundException(String message) {
        super(message);
    }
}
