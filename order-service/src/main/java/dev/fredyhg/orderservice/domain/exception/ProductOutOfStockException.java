package dev.fredyhg.orderservice.domain.exception;

import dev.fredyhg.orderservice.common.domain.response.ProductResponse;
import dev.fredyhg.orderservice.domain.models.product.Product;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductOutOfStockException extends RuntimeException {

    private final List<ProductResponse> unavailableProducts;

    public ProductOutOfStockException(List<ProductResponse> products, String msg) {
        super(msg);
        this.unavailableProducts = products;
    }
}
