package dev.fredyhg.orderservice.common.mapper;

import dev.fredyhg.orderservice.adapter.persist.models.OrderModel;
import dev.fredyhg.orderservice.domain.models.Order;
import dev.fredyhg.orderservice.domain.models.product.Product;

import java.util.List;

public class OrderMapper {

    public static OrderModel orderToModel(Order order) {

        List<String> listOfCode = order.getProducts().stream().map(Product::getProductCode).toList();

        return new OrderModel(order.getId().fromValue(), order.getCustomerId(), listOfCode, order.getOrderStatus(), order.getOrderDate(), order.getTotalPrice());

    }

}
