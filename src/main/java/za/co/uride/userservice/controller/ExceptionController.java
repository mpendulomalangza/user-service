package za.co.uride.userservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import za.co.uride.userservice.exception.AuthException;
import za.co.uride.userservice.exception.FindException;
import za.co.uride.userservice.exception.FoundException;
import za.co.uride.userservice.exception.OTPExceedLimit;
import za.co.uride.userservice.exception.UpdateException;

import java.rmi.ServerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ExceptionController {
    @Value("${error.auth}")
    private String authErrorMessage;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        StringBuilder stringBuilder = new StringBuilder();
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        for (int i = 0; i < objectErrors.size(); i++) {
            String fieldName = ((FieldError) objectErrors.get(i)).getField();
            String errorMessage = objectErrors.get(i).getDefaultMessage();
            stringBuilder.append(fieldName).append(" ").append(errorMessage);
            if (i < objectErrors.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        return new ResponseEntity<>(Map.of("errorMessage", stringBuilder.toString(), "errorCode", HttpStatus.BAD_REQUEST.name()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(FindException.class)
    @ResponseBody
    public ResponseEntity<?> handleFindException(final FindException ex) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("errorMessage", ex.getMessage());
        errors.put("errorCode", HttpStatus.NOT_FOUND.name());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseBody
    public ResponseEntity<?> handleMissingRequestHeaderException(final MissingRequestHeaderException ex) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("errorMessage", ex.getMessage());
        errors.put("errorCode", HttpStatus.NOT_FOUND.name());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OTPExceedLimit.class)
    @ResponseBody
    public ResponseEntity<?> handleOTPExceedLimit(final OTPExceedLimit ex) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("errorMessage", ex.getMessage());
        errors.put("errorCode", HttpStatus.METHOD_NOT_ALLOWED.name());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(AuthException.class)
    @ResponseBody
    public ResponseEntity<?> handleAuthException(final AuthException ex) {
        log.debug(ex.getMessage());
        HashMap<String, String> errors = new HashMap<>();
        errors.put("errorMessage", authErrorMessage);
        errors.put("errorCode", HttpStatus.UNAUTHORIZED.name());
        return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(FoundException.class)
    @ResponseBody
    public ResponseEntity<?> handleFoundException(final FoundException ex) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("errorMessage", ex.getMessage());
        errors.put("errorCode", HttpStatus.FOUND.name());
        return new ResponseEntity<>(errors, HttpStatus.FOUND);
    }

    @ExceptionHandler(UpdateException.class)
    @ResponseBody
    public ResponseEntity<?> handleUpdateException(final UpdateException ex) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("errorMessage", ex.getMessage());
        errors.put("errorCode", HttpStatus.BAD_REQUEST.name());
        return new ResponseEntity<>(errors, HttpStatus.FOUND);
    }

    @ExceptionHandler(ServerException.class)
    @ResponseBody
    public ResponseEntity<?> handleServerException(final ServerException ex) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("errorMessage", ex.getMessage());
        errors.put("errorCode", HttpStatus.NOT_FOUND.name());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
