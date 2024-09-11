package dev.fredyhg.orderservice.domain.port.persist;

import dev.fredyhg.orderservice.domain.models.Order;

public interface OrderPersistPort {
    void save(Order order);
}
