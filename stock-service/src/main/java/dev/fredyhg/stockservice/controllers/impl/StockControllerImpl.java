package dev.fredyhg.stockservice.controllers.impl;

import dev.fredyhg.stockservice.controllers.StockController;
import dev.fredyhg.stockservice.controllers.requests.CheckStockRequest;
import dev.fredyhg.stockservice.controllers.requests.ProductPostRequest;
import dev.fredyhg.stockservice.controllers.responses.CheckStockResponse;
import dev.fredyhg.stockservice.controllers.responses.ResponseMessage;
import dev.fredyhg.stockservice.services.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/stock")
@RequiredArgsConstructor
public class StockControllerImpl implements StockController {

    private final StockService stockService;

    @PostMapping
    @Override
    public ResponseEntity<ResponseMessage> addProductToStock(@RequestBody ProductPostRequest product) {
        log.info("Receive request to add product to stock with code: {}", product.getProductCode());

        stockService.addProductToStock(product);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseMessage
                        .builder()
                        .message("Product added successfully")
                        .code(HttpStatus.CREATED.value())
                        .build());
    }

    @PostMapping("/check-stock")
    @Override
    public ResponseEntity<List<CheckStockResponse>> checkProductStock(@RequestBody CheckStockRequest checkStockRequest) {
        log.info("Receive request to check product stock with product ID: {} and quantity: {}"); //Improve

        return ResponseEntity.status(HttpStatus.OK).body(stockService.checkStock(checkStockRequest));
    }

}
