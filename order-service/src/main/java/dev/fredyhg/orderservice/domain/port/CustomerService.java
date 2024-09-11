package dev.fredyhg.orderservice.domain.port;

import dev.fredyhg.orderservice.common.reponse.CustomerResponse;

public interface CustomerService {
    CustomerResponse findCustomerById(String id);
}
