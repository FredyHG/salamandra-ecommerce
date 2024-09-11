package dev.fredyhg.productservice.controllers.requests;

import java.time.LocalDateTime;

public record ProductStockPostRequest(String productCode, LocalDateTime createAt, Integer quantity) {
}
