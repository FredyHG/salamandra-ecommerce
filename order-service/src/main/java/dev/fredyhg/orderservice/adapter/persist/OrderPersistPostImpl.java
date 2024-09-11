package dev.fredyhg.orderservice.adapter.persist;

import dev.fredyhg.orderservice.adapter.persist.models.OrderModel;
import dev.fredyhg.orderservice.infrastructe.repository.OrderRepository;
import dev.fredyhg.orderservice.common.mapper.OrderMapper;
import dev.fredyhg.orderservice.domain.models.Order;
import dev.fredyhg.orderservice.domain.port.persist.OrderPersistPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderPersistPostImpl implements OrderPersistPort {

    private final OrderRepository repository;

    @Override
    public void save(Order order) {

        OrderModel orderModel = OrderMapper.orderToModel(order);

        repository.save(orderModel);

    }
}
