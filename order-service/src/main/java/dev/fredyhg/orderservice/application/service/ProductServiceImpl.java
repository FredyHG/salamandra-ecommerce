package dev.fredyhg.orderservice.application.service;

import dev.fredyhg.orderservice.common.reponse.CheckStockResponse;
import dev.fredyhg.orderservice.common.request.CheckStockRequest;
import dev.fredyhg.orderservice.domain.port.ProductService;
import dev.fredyhg.orderservice.infrastructe.feign.ProductFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductFeignClient productFeignClient;

    @Override
    public List<CheckStockResponse> checkStock(CheckStockRequest CheckStockRequest) {

        return productFeignClient.checkStock(CheckStockRequest).getBody();
    }
}
