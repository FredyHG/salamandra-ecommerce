package dev.fredyhg.productservice.services;

import dev.fredyhg.productservice.controllers.requests.CheckStockRequest;
import dev.fredyhg.productservice.controllers.requests.ProductPatchRequest;
import dev.fredyhg.productservice.controllers.requests.ProductPostRequest;
import dev.fredyhg.productservice.controllers.responses.CheckStockResponse;
import dev.fredyhg.productservice.controllers.responses.ProductGetResponse;
import dev.fredyhg.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    void createProduct(ProductPostRequest product);
    void updateProduct(ProductPatchRequest product);
    void ensureProductNotExistsByName(String name);
    void disableProductByCode(String productCode);
    Page<ProductGetResponse> getAllProducts(Pageable pageable);
    Product findProductById(UUID productId);
    Product findProductByCode(String productId);
    List<CheckStockResponse> checkStock(CheckStockRequest checkStockRequest);
}
