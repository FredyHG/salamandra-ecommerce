package dev.fredyhg.stockservice.services;

import dev.fredyhg.stockservice.controllers.requests.CheckStockRequest;
import dev.fredyhg.stockservice.controllers.requests.ProductPostRequest;
import dev.fredyhg.stockservice.controllers.responses.CheckStockResponse;

import java.util.List;

public interface StockService {
    void addProductToStock(ProductPostRequest product);
    void ensureProductNotExistInStockByCode(String code);
    List<CheckStockResponse> checkStock(CheckStockRequest checkStockRequest);
}
