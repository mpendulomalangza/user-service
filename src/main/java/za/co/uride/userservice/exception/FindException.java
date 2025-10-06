package za.co.uride.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class FindException extends RuntimeException {
    public FindException(String message) {
        super(message);
    }
}
