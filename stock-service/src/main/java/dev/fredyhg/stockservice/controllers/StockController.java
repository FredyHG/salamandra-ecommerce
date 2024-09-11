package dev.fredyhg.stockservice.controllers;

import dev.fredyhg.stockservice.controllers.requests.CheckStockRequest;
import dev.fredyhg.stockservice.controllers.requests.ProductPostRequest;
import dev.fredyhg.stockservice.controllers.responses.CheckStockResponse;
import dev.fredyhg.stockservice.controllers.responses.ResponseMessage;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StockController {
    ResponseEntity<ResponseMessage> addProductToStock(ProductPostRequest product);
    ResponseEntity<List<CheckStockResponse>> checkProductStock(CheckStockRequest checkStockRequest);
}
