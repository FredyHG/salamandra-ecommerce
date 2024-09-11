package dev.fredyhg.stockservice.controllers.requests;

import lombok.Getter;

@Getter
public class ProductQuantityDTO {
    private String productCode;
    private int quantity;
}
