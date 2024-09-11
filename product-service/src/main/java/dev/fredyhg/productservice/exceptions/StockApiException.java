package dev.fredyhg.productservice.exceptions;

public class StockApiException extends RuntimeException {
    public StockApiException(String msg) {
        super(msg);
    }
}
