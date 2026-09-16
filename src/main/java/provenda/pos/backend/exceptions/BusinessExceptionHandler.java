package provenda.pos.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseException handleEntityNotFoundException(EntityNotFoundException entityNotFoundException){
        return new ResponseException(HttpStatus.NOT_FOUND.value(), entityNotFoundException.getMessage(), entityNotFoundException.getCode());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseException handleBusinessException(BusinessException businessException){
        return new ResponseException(HttpStatus.BAD_REQUEST.value(), businessException.getMessage(), businessException.getCode());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseException handleValidationException(ValidationException validationException){
        return new ResponseException(HttpStatus.NOT_ACCEPTABLE.value(), validationException.getMessage(), validationException.getCode());
    }
}
