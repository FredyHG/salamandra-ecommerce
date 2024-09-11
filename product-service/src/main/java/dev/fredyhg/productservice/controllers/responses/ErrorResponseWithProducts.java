package dev.fredyhg.productservice.controllers.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ErrorResponseWithProducts {
    private Integer statusCode;
    private String description;
    private LocalDateTime timestamp;
    private List<ProductGetResponse> outOfStockProducts;
}
