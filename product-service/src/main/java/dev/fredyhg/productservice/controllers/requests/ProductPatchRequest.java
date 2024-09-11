package dev.fredyhg.productservice.controllers.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@ToString
@Getter
public class ProductPatchRequest {

    @NotNull(message = "Id must not be null")
    private UUID id;
    private String name;
    private BigDecimal price;
    private String description;
}
