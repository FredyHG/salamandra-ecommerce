package dev.fredyhg.stockservice.controllers.responses;

import lombok.Getter;

@Getter
public class CheckStockResponse {
    private String productCode;
    private Integer stockQuantity;
    private boolean available;

    public CheckStockResponse(final String productCode, final Integer stockQuantity, final boolean available) {
        this.productCode = productCode;
        this.stockQuantity = stockQuantity;
        this.available = available;
    }
}
