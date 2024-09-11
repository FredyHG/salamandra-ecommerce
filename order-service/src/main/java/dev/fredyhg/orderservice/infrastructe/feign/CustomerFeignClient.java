package dev.fredyhg.orderservice.infrastructe.feign;

import dev.fredyhg.orderservice.common.reponse.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerFeignClient {
    @GetMapping("/api/v1/customer/{id}")
    ResponseEntity<CustomerResponse> findCustomer(@PathVariable("id") String id);
}
