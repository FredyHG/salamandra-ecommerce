package dev.fredyhg.productservice.exceptions.handlers;

import dev.fredyhg.productservice.controllers.responses.ErrorResponse;
import dev.fredyhg.productservice.exceptions.ProductAlreadyExistsException;
import dev.fredyhg.productservice.exceptions.ProductException;
import dev.fredyhg.productservice.exceptions.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ProductExceptionHandler {

    private static final Map<String, HttpStatus> statusTable = new HashMap<>();

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ErrorResponse> handleProductException(ProductException ex) {
        log.error("Exception handled: {}", ex.getMessage());

        HttpStatus status = mapStatus(ex);

        ErrorResponse responseMessage = ErrorResponse.builder()
                .statusCode(status.value())
                .description(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(responseMessage, status);
    }

    private HttpStatus mapStatus(ProductException ex) {
        return statusTable.getOrDefault(ex.getClass().getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    static {
        statusTable.put(ProductNotFoundException.class.getSimpleName(), HttpStatus.NOT_FOUND);
        statusTable.put(ProductAlreadyExistsException.class.getSimpleName(), HttpStatus.CONFLICT);
    }
}
