package dev.fredyhg.orderservice.infrastructe.repository;

import dev.fredyhg.orderservice.adapter.persist.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, String> {
}
