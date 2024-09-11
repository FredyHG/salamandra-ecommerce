package dev.fredyhg.productservice.controllers.responses;

import lombok.Getter;

@Getter
public class CheckStockResponse {
    private String productCode;
    private Integer stockQuantity;
    private boolean available;
}
