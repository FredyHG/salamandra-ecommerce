package dev.fredyhg.stockservice.services.impl;

import dev.fredyhg.stockservice.controllers.requests.CheckStockRequest;
import dev.fredyhg.stockservice.controllers.requests.ProductPostRequest;
import dev.fredyhg.stockservice.controllers.responses.CheckStockResponse;
import dev.fredyhg.stockservice.exceptions.ProductAlreadyExistInStockException;
import dev.fredyhg.stockservice.exceptions.ProductNotFoundException;
import dev.fredyhg.stockservice.mappers.StockMapper;
import dev.fredyhg.stockservice.models.Stock;
import dev.fredyhg.stockservice.repositories.StockRepository;
import dev.fredyhg.stockservice.services.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    public void addProductToStock(ProductPostRequest product) {
        log.info("Adding product to stock with product code: {}", product.getProductCode());

        ensureProductNotExistInStockByCode(product.getProductCode());

        Stock stockToBeSaved = StockMapper.productToStock(product);

        stockRepository.save(stockToBeSaved);
        log.info("Successfully added product to stock");
    }

    @Override
    public void ensureProductNotExistInStockByCode(String code) {
        log.info("Checking if stock with product code: {}", code);

        stockRepository.findByProductCode(code).ifPresent(s -> {
            throw new ProductAlreadyExistInStockException("Product already exist in stock");
        });
    }

    @Override
    public List<CheckStockResponse> checkStock(CheckStockRequest checkStockRequest) {

        return checkStockRequest.getProducts().stream()
                .map(product -> {
                    String code = product.getProductCode();
                    int quantity = product.getQuantity();

                    Stock stock = stockRepository.findByProductCode(code)
                            .orElseThrow(() -> new ProductNotFoundException("Product not found with code: " + code));

                    boolean isAvailable = stock.getQuantity() >= quantity;

                    return new CheckStockResponse(stock.getProductCode(), stock.getQuantity(), isAvailable);
                })
                .toList();
    }
}
