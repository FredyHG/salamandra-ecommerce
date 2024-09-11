package dev.fredyhg.stockservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@ToString
@Entity
@Getter
@Setter
@Table(name = "tb_stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true, nullable = false)
    private String productCode;
    private Integer quantity;
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Stock(String productCode, LocalDateTime createdAt, Integer quantity) {
        this.quantity = 0;
        this.productCode = productCode;
        this.createdAt = createdAt;
        this.updatedAt = LocalDateTime.now();
        this.quantity = quantity > 0 ? quantity : 0;
    }

    public Stock() {
    }
}