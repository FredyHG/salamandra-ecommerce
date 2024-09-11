package dev.fredyhg.stockservice.controllers.requests;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProductPostRequest {
    private String productCode;
    private LocalDateTime createAt;
    private final Integer quantity = 0;
}
