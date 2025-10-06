package za.co.uride.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UpdateException extends RuntimeException {
    public UpdateException(String message) {
        super(message);
    }
}
