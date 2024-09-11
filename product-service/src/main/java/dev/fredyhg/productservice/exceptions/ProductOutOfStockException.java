package dev.fredyhg.productservice.exceptions;

import com.google.gson.Gson;
import dev.fredyhg.productservice.controllers.responses.ProductGetResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductOutOfStockException extends RuntimeException {

    private List<ProductGetResponse> unavailableProducts;

    public ProductOutOfStockException(List<ProductGetResponse> products) {
        super("Message");
        this.unavailableProducts = products;
    }
}
