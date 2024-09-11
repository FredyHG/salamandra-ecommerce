package dev.fredyhg.stockservice.exceptions;

public class ProductOutOfStockException extends StockException{
    public ProductOutOfStockException(String msg) {
        super(msg);
    }
}
