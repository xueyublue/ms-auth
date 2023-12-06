package sg.darren.ms.auth.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(Exception exception) {
        log.error(new Date().getTime() + ": " + exception.getMessage(), exception);
        return new ResponseEntity<>("Unhandled exception occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException exception) {
        log.error(new Date().getTime() + ": " + exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException exception) {
        log.error(new Date().getTime() + ": " + exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error(new Date().getTime() + ": " + exception.getMessage());
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.error("MethodArgumentNotValidException: " + errors);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidInputException.class)
    public ResponseEntity<Object> handleInvalidInputException(InvalidInputException exception) {
        log.error(new Date().getTime() + ": " + exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DataNotFoundException.class)
    public ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException exception) {
        log.error(new Date().getTime() + ": " + exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DataDuplicateException.class)
    public ResponseEntity<Object> handleDataDuplicateException(DataDuplicateException exception) {
        log.error(new Date().getTime() + ": " + exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

}
