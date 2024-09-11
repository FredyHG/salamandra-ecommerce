package dev.fredyhg.productservice.exceptions;

public class ProductAlreadyExistsException extends ProductException {
    public ProductAlreadyExistsException(String msg) {
        super(msg);
    }
}
