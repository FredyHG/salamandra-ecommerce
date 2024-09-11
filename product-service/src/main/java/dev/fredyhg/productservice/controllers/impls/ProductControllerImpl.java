package dev.fredyhg.productservice.controllers.impls;

import dev.fredyhg.productservice.controllers.ProductController;
import dev.fredyhg.productservice.controllers.requests.CheckStockRequest;
import dev.fredyhg.productservice.controllers.requests.ProductPatchRequest;
import dev.fredyhg.productservice.controllers.requests.ProductPostRequest;
import dev.fredyhg.productservice.controllers.responses.CheckStockResponse;
import dev.fredyhg.productservice.controllers.responses.ProductGetResponse;
import dev.fredyhg.productservice.controllers.responses.ResponseMessage;
import dev.fredyhg.productservice.feigns.StockFeignClient;
import dev.fredyhg.productservice.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;
    private final StockFeignClient stockFeignClient;

    @PostMapping("/check-stock")
    @Override
    public ResponseEntity<List<CheckStockResponse>> checkStock(@RequestBody CheckStockRequest checkStockRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.checkStock(checkStockRequest));
    }

    @PostMapping
    @Override
    public ResponseEntity<ResponseMessage> createProduct(@RequestBody @Valid ProductPostRequest request) {
        log.info("Receive request to save product with name: {}", request.getName());

        productService.createProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseMessage.builder()
                .code(HttpStatus.CREATED.value())
                .message("Product created successfully")
                .build());
    }

    @PatchMapping
    @Override
    public ResponseEntity<ResponseMessage> updateProductInfos(@RequestBody ProductPatchRequest product) {
        log.info("Receive request to update product with id: {}", product.getId());

        productService.updateProduct(product);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseMessage
                        .builder()
                        .message("Product updated successfully.")
                        .code(HttpStatus.OK.value())
                        .build());
    }

    @PatchMapping("disable/{code}")
    @Override
    public ResponseEntity<ResponseMessage> disableProductByCode(@PathVariable(name = "code") String productCode) {

        productService.disableProductByCode(productCode);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseMessage
                        .builder()
                        .message("Product disabled successfully.")
                        .code(HttpStatus.OK.value())
                        .build());
    }

    @GetMapping
    @Override
    public ResponseEntity<Page<ProductGetResponse>> getAllProducts(Pageable pageable) {
        log.info("Receive request to get all products");

        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts(pageable));
    }
}