package dev.fredyhg.productservice.exceptions;

public class ProductNotFoundException extends ProductException {
    public ProductNotFoundException(String msg) {
        super(msg);
    }
}
