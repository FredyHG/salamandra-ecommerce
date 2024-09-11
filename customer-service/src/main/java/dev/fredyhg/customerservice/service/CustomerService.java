package dev.fredyhg.customerservice.service;

import dev.fredyhg.customerservice.controller.request.CustomerPostRequest;
import dev.fredyhg.customerservice.controller.response.CustomerGetResponse;
import dev.fredyhg.customerservice.model.Customer;

public interface CustomerService {
    void registerCustomer(CustomerPostRequest customer);
    Customer ensureCustomerExistsByEmail(String email);
    Customer ensureCustomerExistsByCPF(String cpf);
    Customer ensureCustomerExistsById(String id);
    void ensureCustomerNotExistsByEmail(String email);
    void ensureCustomerNotExistsByCPF(String cpf);
    CustomerGetResponse findGetCustomerById(String id);
}
