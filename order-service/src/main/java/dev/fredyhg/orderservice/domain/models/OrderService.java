package dev.fredyhg.orderservice.domain.models;

import dev.fredyhg.orderservice.common.dto.ProductQuantityDTO;
import dev.fredyhg.orderservice.common.reponse.CheckStockResponse;
import dev.fredyhg.orderservice.common.reponse.CustomerResponse;
import dev.fredyhg.orderservice.common.request.CheckStockRequest;
import dev.fredyhg.orderservice.domain.models.product.Product;
import dev.fredyhg.orderservice.domain.port.CustomerService;
import dev.fredyhg.orderservice.domain.port.ProductService;
import dev.fredyhg.orderservice.domain.port.persist.OrderPersistPort;
import dev.fredyhg.orderservice.domain.port.persist.ReceiveOrderPort;

import java.util.List;

public class OrderService implements ReceiveOrderPort {

    private final OrderPersistPort orderPersistPort;
    private final ProductService productService;
    private final CustomerService customerService;

    public OrderService(OrderPersistPort orderPersistPort, ProductService productService, CustomerService customerService) {
        this.orderPersistPort = orderPersistPort;
        this.productService = productService;
        this.customerService = customerService;
    }

    @Override
    public Order save(Order order) {
        checkStock(order.getProducts());
        checkCustomerExists(order.getCustomerId());

        orderPersistPort.save(order);

        return order;
    }

    private void checkStock(List<Product> products) {
        List<ProductQuantityDTO> list = products.stream()
                .map(product -> new ProductQuantityDTO(product.getProductCode(), product.getQuantity()))
                .toList();

        List<CheckStockResponse> response = productService.checkStock(new CheckStockRequest(list));

        response.forEach(checkStockResponse -> {
            if(!checkStockResponse.isAvailable()) {
                throw new RuntimeException("Available false"); //IMPROVE MESSAGE AND CHANGE
            }
        });
    }

    private void checkCustomerExists(String customerId) {
        CustomerResponse customerById = customerService.findCustomerById(customerId);

        if(customerById == null) {
            throw new RuntimeException("Customer not found"); // IMPROVE MESSAGE AND CREATE
        }
    }
}
