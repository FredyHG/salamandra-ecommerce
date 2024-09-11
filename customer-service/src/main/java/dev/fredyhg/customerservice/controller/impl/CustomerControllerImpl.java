package dev.fredyhg.customerservice.controller.impl;

import dev.fredyhg.customerservice.controller.CustomerController;
import dev.fredyhg.customerservice.controller.request.CustomerPostRequest;
import dev.fredyhg.customerservice.controller.response.CustomerGetResponse;
import dev.fredyhg.customerservice.controller.response.ResponseMessage;
import dev.fredyhg.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerControllerImpl implements CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    @Override
    public ResponseEntity<ResponseMessage> registerCustomer(@RequestBody CustomerPostRequest customerRequest) {

        log.info("Receive request to register a new Customer with cpf: {}", customerRequest.getCpf());

        customerService.registerCustomer(customerRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseMessage.builder()
                        .code(201)
                        .message("Customer created successfully")
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<CustomerGetResponse> findCustomerById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findGetCustomerById(id));
    }
}
