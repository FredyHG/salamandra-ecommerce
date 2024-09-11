package dev.fredyhg.orderservice.domain.port;

import dev.fredyhg.orderservice.common.reponse.CheckStockResponse;
import dev.fredyhg.orderservice.common.request.CheckStockRequest;

import java.util.List;

public interface ProductService {
    List<CheckStockResponse> checkStock(CheckStockRequest checkStockRequest);
}
