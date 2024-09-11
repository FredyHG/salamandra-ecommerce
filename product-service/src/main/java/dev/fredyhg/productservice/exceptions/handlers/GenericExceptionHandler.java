package dev.fredyhg.productservice.exceptions.handlers;

import dev.fredyhg.productservice.controllers.responses.ErrorResponse;
import dev.fredyhg.productservice.controllers.responses.ErrorResponseWithProducts;
import dev.fredyhg.productservice.exceptions.BeanUtilsParserExceptionClass;
import dev.fredyhg.productservice.exceptions.GenericException;
import dev.fredyhg.productservice.exceptions.ProductOutOfStockException;
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
public class GenericExceptionHandler {

    private static final Map<String, HttpStatus> statusTable = new HashMap<>();

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponse> handleProductException(GenericException ex) {
        log.error("Exception handled: {}", ex.getMessage());

        HttpStatus status = mapStatus(ex);

        ErrorResponse responseMessage = ErrorResponse.builder()
                .statusCode(status.value())
                .description(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(responseMessage, status);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleProductException(IllegalArgumentException ex) {
        log.error("Exception handled: {}", ex.getMessage());


        ErrorResponse responseMessage = ErrorResponse.builder()
                .statusCode(HttpStatus.CONFLICT.value())
                .description(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(responseMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProductOutOfStockException.class)
    public ResponseEntity<ErrorResponseWithProducts> handleProductException(ProductOutOfStockException ex) {
        log.error("Exception handled: {}", ex.getMessage());


        ErrorResponseWithProducts responseMessage = ErrorResponseWithProducts.builder()
                .statusCode(HttpStatus.CONFLICT.value())
                .description(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .outOfStockProducts(ex.getUnavailableProducts())
                .build();
        return new ResponseEntity<>(responseMessage, HttpStatus.CONFLICT);
    }

    private HttpStatus mapStatus(GenericException ex) {
        return statusTable.getOrDefault(ex.getClass().getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    static {
        statusTable.put(BeanUtilsParserExceptionClass.class.getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
