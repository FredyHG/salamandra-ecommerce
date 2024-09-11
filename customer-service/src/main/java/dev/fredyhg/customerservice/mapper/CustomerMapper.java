package dev.fredyhg.customerservice.mapper;

import dev.fredyhg.customerservice.controller.request.CustomerPostRequest;
import dev.fredyhg.customerservice.controller.response.CustomerGetResponse;
import dev.fredyhg.customerservice.model.Customer;

public class CustomerMapper {
    public static Customer postRequestToModel(CustomerPostRequest request) {
        return new Customer(request.getCpf(),
                request.getPhone(),
                request.getFirstName(),
                request.getLastName(),
                request.getEmail());
    }

    public static CustomerGetResponse customerToResponse(Customer customer) {
        return new CustomerGetResponse(customer.getId(),
                customer.getCpf(),
                customer.getPhone(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddresses());
    }
}
