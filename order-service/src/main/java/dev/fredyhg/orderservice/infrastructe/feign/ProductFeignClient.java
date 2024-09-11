package dev.fredyhg.orderservice.infrastructe.feign;

import dev.fredyhg.orderservice.common.reponse.CheckStockResponse;
import dev.fredyhg.orderservice.common.request.CheckStockRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductFeignClient {
    @PostMapping("/api/v1/stock/check-stock")
    ResponseEntity<List<CheckStockResponse>> checkStock(@RequestBody CheckStockRequest checkStockRequest);
}
