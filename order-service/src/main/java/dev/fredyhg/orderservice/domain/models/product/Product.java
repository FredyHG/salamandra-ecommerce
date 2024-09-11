package dev.fredyhg.orderservice.domain.models.product;

import dev.fredyhg.orderservice.common.domain.Aggregate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends Aggregate<ProductId> {
    private String productCode;
    private Integer quantity;
    private Double price;

    protected Product(String productCode, Integer quantity, Double price) {
        super(new ProductId());
        this.productCode = productCode;
        this.quantity = quantity;
        this.price = price;
    }
}
