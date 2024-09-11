package dev.fredyhg.productservice.factories;

import dev.fredyhg.productservice.controllers.requests.ProductPatchRequest;
import dev.fredyhg.productservice.controllers.requests.ProductPostRequest;
import dev.fredyhg.productservice.models.Product;
import org.instancio.Instancio;
import org.instancio.Model;

import java.util.UUID;

import static org.instancio.Select.field;

public class ProductFactory {
    private static final Model<ProductPostRequest> PRODUCT_POST_REQUEST_MODEL =
            Instancio.of(ProductPostRequest.class)
                    .toModel();

    private static final Model<Product> PRODUCT_MODEL =
            Instancio.of(Product.class)
                    .toModel();

    private static final Model<ProductPatchRequest> PRODUCT_PATCH_REQUEST_MODEL =
            Instancio.of(ProductPatchRequest.class)
                    .toModel();

    public static ProductPostRequest createProductPostRequest() {
        return Instancio.of(PRODUCT_POST_REQUEST_MODEL)
                .create();
    }

    public static Product createProductWithName(String productName) {
        return Instancio.of(PRODUCT_MODEL)
                .set(field(Product::getName), productName)
                .create();
    }

    public static Product createValidProduct() {
        return Instancio.of(PRODUCT_MODEL)
                .create();
    }

    public static Product createValidProductWithId() {
        return Instancio.of(PRODUCT_MODEL)
                .set(field(Product::getId), UUID.randomUUID())
                .create();
    }

    public static ProductPatchRequest createProductPatchRequest() {
        return Instancio.of(PRODUCT_PATCH_REQUEST_MODEL)
                .create();
    }
}
