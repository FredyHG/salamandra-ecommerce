package dev.fredyhg.stockservice.exceptions;

public class ProductAlreadyExistInStockException extends StockException{
    public ProductAlreadyExistInStockException(String msg) {
        super(msg);
    }
}
