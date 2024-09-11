package dev.fredyhg.orderservice.domain.port.persist;

import dev.fredyhg.orderservice.domain.models.Order;

public interface ReceiveOrderPort {
    Order save(Order order);
}
