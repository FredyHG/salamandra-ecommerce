package dev.fredyhg.stockservice.exceptions.handlers;

import dev.fredyhg.stockservice.controllers.responses.ErrorResponse;
import dev.fredyhg.stockservice.exceptions.ProductAlreadyExistInStockException;
import dev.fredyhg.stockservice.exceptions.StockException;
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
public class StockExceptionHandler {

    private static final Map<String, HttpStatus> statusTable = new HashMap<>();

    @ExceptionHandler(StockException.class)
    public ResponseEntity<ErrorResponse> handleProductException(StockException ex) {
        log.error("Exception handled: {}", ex.getMessage());

        HttpStatus status = mapStatus(ex);

        ErrorResponse responseMessage = ErrorResponse.builder()
                .statusCode(status.value())
                .description(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(responseMessage, status);
    }

    private HttpStatus mapStatus(StockException ex) {
        return statusTable.getOrDefault(ex.getClass().getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    static {
        statusTable.put(ProductAlreadyExistInStockException.class.getSimpleName(), HttpStatus.CONFLICT);
    }

}
