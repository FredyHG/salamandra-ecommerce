package dev.fredyhg.productservice.controllers.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductPostRequest {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Size(min = 2, max = 300, message = "Description must be between 2 and 300 characters")
    private String description;

    @NotNull(message = "Price cannot be null")
    private BigDecimal price;

    private final Integer quantity = 0;
}
