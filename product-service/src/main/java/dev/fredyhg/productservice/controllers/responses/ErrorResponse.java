package dev.fredyhg.productservice.controllers.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private Integer statusCode;
    private String description;
    private LocalDateTime timestamp;
}
