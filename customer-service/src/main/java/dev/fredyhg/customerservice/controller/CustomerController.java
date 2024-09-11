package dev.fredyhg.customerservice.controller;

import dev.fredyhg.customerservice.controller.request.CustomerPostRequest;
import dev.fredyhg.customerservice.controller.response.CustomerGetResponse;
import dev.fredyhg.customerservice.controller.response.ResponseMessage;
import org.springframework.http.ResponseEntity;

public interface CustomerController {
    ResponseEntity<ResponseMessage> registerCustomer(CustomerPostRequest customerRequest);
    ResponseEntity<CustomerGetResponse> findCustomerById(String id);
}
