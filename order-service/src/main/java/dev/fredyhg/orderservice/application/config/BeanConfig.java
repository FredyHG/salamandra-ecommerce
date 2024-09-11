package dev.fredyhg.orderservice.application.config;

import dev.fredyhg.orderservice.domain.models.OrderService;
import dev.fredyhg.orderservice.domain.port.CustomerService;
import dev.fredyhg.orderservice.domain.port.ProductService;
import dev.fredyhg.orderservice.domain.port.persist.OrderPersistPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public OrderService orderService(OrderPersistPort orderPersistPort, ProductService productService, CustomerService customerService) {
        return new OrderService(orderPersistPort, productService, customerService);
    }

}
