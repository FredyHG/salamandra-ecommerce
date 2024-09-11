package dev.fredyhg.orderservice.domain.models;

import dev.fredyhg.orderservice.common.domain.Aggregate;
import dev.fredyhg.orderservice.domain.enums.OrderStatus;
import dev.fredyhg.orderservice.domain.models.product.Product;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import static dev.fredyhg.orderservice.common.AssertionConcern.assertArgumentNotEmpty;
import static dev.fredyhg.orderservice.common.AssertionConcern.assertListNotEmpty;

@Getter
public class Order extends Aggregate<OrderId> {
    private String customerId;
    private List<Product> products;
    private OrderStatus orderStatus;
    private LocalDateTime orderDate;
    private Double totalPrice;

    public Order(String customerId, List<Product> products) {
        super(new OrderId());

        assertListNotEmpty(products, "Product list is empty");
        assertArgumentNotEmpty(customerId, "Customer ID is empty");

        this.customerId = customerId;
        this.products = products;
        this.orderStatus = OrderStatus.STARTED;
        this.totalPrice = calculateTotalPrice();
    }

    public Double calculateTotalPrice() {
        return this.getProducts()
                .stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
}
