package dev.fredyhg.orderservice.common.request;

import dev.fredyhg.orderservice.common.dto.ProductQuantityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CheckStockRequest {
    private List<ProductQuantityDTO> products;
}
