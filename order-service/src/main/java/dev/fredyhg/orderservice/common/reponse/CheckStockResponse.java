package dev.fredyhg.orderservice.common.reponse;

import lombok.Getter;

@Getter
public class CheckStockResponse {
    private String productCode;
    private Integer stockQuantity;
    private boolean available;
}