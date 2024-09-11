package dev.fredyhg.orderservice.application.service;

import dev.fredyhg.orderservice.common.reponse.CustomerResponse;
import dev.fredyhg.orderservice.domain.port.CustomerService;
import dev.fredyhg.orderservice.infrastructe.feign.CustomerFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerFeignClient customerFeignClient;

    @Override
    public CustomerResponse findCustomerById(String id) {
        return customerFeignClient.findCustomer(id).getBody();
    }
}
