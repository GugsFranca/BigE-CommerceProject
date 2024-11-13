package store.product.exception.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import store.product.exception.ProductPurchaseException;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductPurchaseException.class)
    public ResponseEntity<String> handler(ProductPurchaseException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> handler(DuplicateKeyException e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handler(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handler(MethodArgumentNotValidException e) {
        var errors = new HashMap<String, String>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(errors));
    }
}
