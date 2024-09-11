package dev.fredyhg.stockservice.mappers;

import dev.fredyhg.stockservice.controllers.requests.ProductPostRequest;
import dev.fredyhg.stockservice.models.Stock;

public class StockMapper {
    public static Stock productToStock(ProductPostRequest product) {
        return new Stock(product.getProductCode(), product.getCreateAt(), product.getQuantity());
    }

    private StockMapper() {}
}
