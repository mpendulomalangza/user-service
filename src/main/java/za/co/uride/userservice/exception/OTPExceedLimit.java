package za.co.uride.userservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OTPExceedLimit extends RuntimeException {
    public OTPExceedLimit(String message){
        super(message);
    }
}
