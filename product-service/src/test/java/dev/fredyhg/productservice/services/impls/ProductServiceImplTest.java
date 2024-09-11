package dev.fredyhg.productservice.services.impls;

import dev.fredyhg.productservice.controllers.requests.ProductPatchRequest;
import dev.fredyhg.productservice.controllers.requests.ProductPostRequest;
import dev.fredyhg.productservice.controllers.responses.ProductGetResponse;
import dev.fredyhg.productservice.exceptions.ProductAlreadyExistsException;
import dev.fredyhg.productservice.exceptions.ProductNotFoundException;
import dev.fredyhg.productservice.factories.ProductFactory;
import dev.fredyhg.productservice.models.Product;
import dev.fredyhg.productservice.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void testSave_NewProduct() {
        ProductPostRequest productPostRequest = ProductFactory.createProductPostRequest();

        productService.createProduct(productPostRequest);

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository, times(1)).save(productArgumentCaptor.capture());
        var productCapturedValue = productArgumentCaptor.getValue();

        assertNotNull(productCapturedValue.getCode());
        assertNotNull(productCapturedValue.getName());
    }

    @Test
    void testThrowsExceptions_WhenProductAlreadyExists() {
        ProductPostRequest productPostRequest = ProductFactory.createProductPostRequest();

        Product alreadyExistProduct = ProductFactory.createProductWithName(productPostRequest.getName());

        when(productRepository.findByName(productPostRequest.getName())).thenReturn(Optional.of(alreadyExistProduct));

        ProductAlreadyExistsException exception = assertThrows(ProductAlreadyExistsException.class,
                () -> productService.createProduct(productPostRequest));

        String expectedMessage = "Product already exists with this name: " + alreadyExistProduct.getName();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(productRepository, times(1)).findByName(productPostRequest.getName());
    }

    @Test
    void testFindProductById_Success() {
        Product alreadyExistProduct = ProductFactory.createValidProductWithId();
        UUID id = alreadyExistProduct.getId();

        when(productRepository.findById(id)).thenReturn(Optional.of(alreadyExistProduct));

        Product foundProduct = productService.findProductById(id);

        assertNotNull(foundProduct);
        assertEquals(id, foundProduct.getId());
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    void testFindProductById_ProductNotFound() {
        UUID id = UUID.randomUUID();
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            productService.findProductById(id);
        });

        String expectedMessage = "Product not found.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    void disableProductById() {
        Product alreadyExistProduct = ProductFactory.createValidProductWithId();
        String code = alreadyExistProduct.getCode();

        when(productRepository.findByCode(code)).thenReturn(Optional.of(alreadyExistProduct));

        productService.disableProductByCode(code);

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository, times(1)).save(productArgumentCaptor.capture());
        var productCapturedValue = productArgumentCaptor.getValue();

        assertNotNull(productCapturedValue.getId());
        assertFalse(productCapturedValue.isActive());
        verify(productRepository, times(1)).findByCode(code);
    }

    @Test
    void testGetAllProducts() {
        Pageable pageable = PageRequest.of(0, 10);

        Product product1 = ProductFactory.createValidProduct();
        Product product2 = ProductFactory.createValidProduct();

        List<Product> products = List.of(product1, product2);
        Page<Product> productPage = new PageImpl<>(products, pageable, products.size());

        when(productRepository.findAll(pageable)).thenReturn(productPage);

        Page<ProductGetResponse> result = productService.getAllProducts(pageable);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        verify(productRepository, times(1)).findAll(pageable);
    }

    @Test
    void testUpdateProduct() {
        ProductPatchRequest productPatchRequest = ProductFactory.createProductPatchRequest();

        Product existingProduct = ProductFactory.createValidProduct();


        when(productRepository.findById(productPatchRequest.getId())).thenReturn(Optional.of(existingProduct));

        productService.updateProduct(productPatchRequest);

        verify(productRepository, times(1)).findById(productPatchRequest.getId());
        verify(productRepository, times(1)).save(existingProduct);

        assertEquals(productPatchRequest.getName(), existingProduct.getName());
    }
}