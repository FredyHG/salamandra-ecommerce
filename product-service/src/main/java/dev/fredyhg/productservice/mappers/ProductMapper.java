package dev.fredyhg.productservice.mappers;

import dev.fredyhg.productservice.controllers.requests.ProductPatchRequest;
import dev.fredyhg.productservice.controllers.requests.ProductPostRequest;
import dev.fredyhg.productservice.controllers.responses.ProductGetResponse;
import dev.fredyhg.productservice.models.Product;

public class ProductMapper {

    public static Product requestToModel(ProductPostRequest request) {
        return new Product(
                request.getDescription(),
                request.getPrice(),
                request.getName());
    }

    public static ProductGetResponse toGetRequest(Product product) {
        return new ProductGetResponse(
                product.getName(),
                product.getCode(),
                product.getPrice(),
                product.getDescription(),
                product.isActive());
    }

    public static Product productPatchRequestToProduct(ProductPatchRequest productPatchRequest) {
        return new Product(
                productPatchRequest.getDescription(),
                productPatchRequest.getPrice(),
                productPatchRequest.getName());
    }

    private ProductMapper() {
    }
}
