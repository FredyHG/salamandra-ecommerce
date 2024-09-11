package dev.fredyhg.productservice.feigns;

import dev.fredyhg.productservice.configs.StockFeignConfig;
import dev.fredyhg.productservice.controllers.requests.CheckStockRequest;
import dev.fredyhg.productservice.controllers.requests.ProductStockPostRequest;
import dev.fredyhg.productservice.controllers.responses.CheckStockResponse;
import dev.fredyhg.productservice.controllers.responses.ResponseMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "stock-service", configuration = StockFeignConfig.class)
public interface StockFeignClient {
    
    @PostMapping("/api/v1/stock")
    ResponseEntity<ResponseMessage> addProductToStock(@RequestBody ProductStockPostRequest request);

    @PostMapping("/api/v1/stock/check-stock")
    ResponseEntity<List<CheckStockResponse>> checkStock(@RequestBody CheckStockRequest checkStockRequest);
}
