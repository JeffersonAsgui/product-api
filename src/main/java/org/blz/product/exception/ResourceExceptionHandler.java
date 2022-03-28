package org.blz.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Error> objectNotFoundException(ObjectNotFoundException e) {
        Error error = Error.builder()
                .timestamp(System.currentTimeMillis())
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegratyViolationException.class)
    public ResponseEntity<Error> objectNotFoundException(DataIntegratyViolationException e) {
        Error error = Error.builder()
                .timestamp(System.currentTimeMillis())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var errors = createErrorList(e.getBindingResult());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    private List<Error> createErrorList(BindingResult bindingResult) {
        List<Error> listerror = new ArrayList<>();
        for (var field : bindingResult.getFieldErrors()) {
            listerror.add(Error.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(field.getField() + " : " + field.getDefaultMessage())
                    .build()
            );
        }
        return listerror;
    }
}
