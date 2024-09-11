package dev.fredyhg.productservice.controllers;

import dev.fredyhg.productservice.controllers.requests.CheckStockRequest;
import dev.fredyhg.productservice.controllers.requests.ProductPatchRequest;
import dev.fredyhg.productservice.controllers.requests.ProductPostRequest;
import dev.fredyhg.productservice.controllers.responses.CheckStockResponse;
import dev.fredyhg.productservice.controllers.responses.ProductGetResponse;
import dev.fredyhg.productservice.controllers.responses.ResponseMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductController {
    ResponseEntity<List<CheckStockResponse>> checkStock(CheckStockRequest checkStockRequest);
    ResponseEntity<ResponseMessage> createProduct(ProductPostRequest request);
    ResponseEntity<ResponseMessage> updateProductInfos(ProductPatchRequest product);
    ResponseEntity<ResponseMessage> disableProductByCode(String productId);
    ResponseEntity<Page<ProductGetResponse>> getAllProducts(Pageable pageable);
}
