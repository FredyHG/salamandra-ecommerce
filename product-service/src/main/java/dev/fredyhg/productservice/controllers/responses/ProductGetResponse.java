package dev.fredyhg.productservice.controllers.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
public class ProductGetResponse {
    private String name;
    private String code;
    private BigDecimal price;
    private String description;
    private boolean active;

    @Override
    public String toString() {
        return "Product {" +
                "Name='" + name + '\'' +
                ", Code='" + code + '\'' +
                ", Price=" + price +
                ", Description='" + description + '\'' +
                ", Active=" + (active ? "Yes" : "No") +
                '}';
    }
}
