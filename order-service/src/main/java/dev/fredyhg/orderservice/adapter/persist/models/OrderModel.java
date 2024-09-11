package dev.fredyhg.orderservice.adapter.persist.models;

import dev.fredyhg.orderservice.domain.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Table(name = "tb_order")
public class OrderModel {

    @Id
    private String id;
    @Column(name = "customer_id")
    private String customerId;
    @ElementCollection
    private List<String> productsIds;
    @Enumerated(EnumType.STRING) //CHANGE TO ORDINAL
    private OrderStatus status;
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    @Column(name = "total_price")
    private Double totalPrice;

    protected OrderModel() {}

    public OrderModel(String id, String customerId, List<String> productsIds, OrderStatus status, LocalDateTime orderDate, Double totalPrice) {
        this.id = id;
        this.customerId = customerId;
        this.productsIds = productsIds;
        this.status = status;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }
}
