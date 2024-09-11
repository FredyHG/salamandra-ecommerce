package dev.fredyhg.orderservice.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductQuantityDTO {
    private String productCode;
    private int quantity;
}
