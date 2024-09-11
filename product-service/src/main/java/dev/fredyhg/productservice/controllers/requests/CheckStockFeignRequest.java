package dev.fredyhg.productservice.controllers.requests;

import lombok.Getter;

@Getter
public class CheckStockFeignRequest {
    private String code;
    private Integer quantity;

    public CheckStockFeignRequest(String code, Integer quantity) {
        this.code = code;
        this.quantity = quantity;
    }
}
