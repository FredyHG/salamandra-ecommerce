package dev.fredyhg.productservice.services.impls;

import dev.fredyhg.productservice.configs.BeanPropertyUtils;
import dev.fredyhg.productservice.controllers.requests.*;
import dev.fredyhg.productservice.controllers.responses.CheckStockResponse;
import dev.fredyhg.productservice.controllers.responses.ProductGetResponse;
import dev.fredyhg.productservice.controllers.responses.ResponseMessage;
import dev.fredyhg.productservice.exceptions.ProductAlreadyExistsException;
import dev.fredyhg.productservice.exceptions.ProductNotFoundException;
import dev.fredyhg.productservice.exceptions.ProductOutOfStockException;
import dev.fredyhg.productservice.exceptions.StockApiException;
import dev.fredyhg.productservice.feigns.StockFeignClient;
import dev.fredyhg.productservice.mappers.ProductMapper;
import dev.fredyhg.productservice.models.Product;
import dev.fredyhg.productservice.repositories.ProductRepository;
import dev.fredyhg.productservice.services.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StockFeignClient stockFeignClient;

    @Transactional
    @Override
    public void createProduct(ProductPostRequest request) {
        log.info("Creating product with nome: {}", request.getName());

        ensureProductNotExistsByName(request.getName());

        Product productToBeSaved = ProductMapper.requestToModel(request);

        productRepository.save(productToBeSaved);
        log.info("Successfully created product: {}", productToBeSaved.getName());

        ResponseEntity<ResponseMessage> response =
                stockFeignClient.addProductToStock(new ProductStockPostRequest(productToBeSaved.getCode(), productToBeSaved.getCreatedAt(), request.getQuantity()));

        if(response.getStatusCode().is4xxClientError()) {
            throw new StockApiException(response.getBody().getMessage());
        }
    }

    @Override
    public void updateProduct(ProductPatchRequest productPatchRequest) {

        log.info("Updating product with id: {}", productPatchRequest.getId());

        Product productToBeUpdated = findProductById(productPatchRequest.getId());
        Product product = ProductMapper.productPatchRequestToProduct(productPatchRequest);

        BeanPropertyUtils.copyNonNullProperties(product, productToBeUpdated);

        log.info("Successfully updated product with id: {}", productToBeUpdated.getId());
        productRepository.save(productToBeUpdated);
    }

    @Override
    public void ensureProductNotExistsByName(String name) {
        log.info("Checking if product with name: {}", name);

        productRepository.findByName(name).ifPresent(p -> {
            throw new ProductAlreadyExistsException("Product already exists with this name: " + name);
        });
    }

    @Override
    public void disableProductByCode(String productCode) {
        log.info("Disabling product by code: {}", productCode);

        Product product = findProductByCode(productCode);
        product.disableProduct(product);

        productRepository.save(product);
        log.info("Product with code: {} has been disabled", productCode);
    }

    @Override
    public Page<ProductGetResponse> getAllProducts(Pageable pageable) {
        log.info("Getting all products");

        return productRepository.findAll(pageable).map(ProductMapper::toGetRequest);
    }

    @Override
    public Product findProductById(UUID productId) {
        log.info("Finding product by id: {}", productId);

        return productRepository.findById(productId)
                .orElseThrow(
                        () -> new ProductNotFoundException("Product not found with id: " + productId));
    }

    @Override
    public Product findProductByCode(String productCode) {
        log.info("Finding product by code: {}", productCode);

        return productRepository.findByCode(productCode)
                .orElseThrow(
                        () -> new ProductNotFoundException("Product not found with code: " + productCode));
    }

    @Override
    public List<CheckStockResponse> checkStock(CheckStockRequest stockRequest) {
        return stockFeignClient.checkStock(stockRequest).getBody();
    }
}
