package dev.fredyhg.productservice.controllers.requests;

import lombok.Getter;

import java.util.List;

@Getter
public class CheckStockRequest {
    private List<ProductQuantityDTO> products;
}
