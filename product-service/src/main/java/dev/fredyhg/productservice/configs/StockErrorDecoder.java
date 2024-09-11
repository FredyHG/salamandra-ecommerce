package dev.fredyhg.productservice.configs;

import dev.fredyhg.productservice.exceptions.StockApiException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class StockErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();


    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()) {
            case 409:
                return new StockApiException("Product already exist in stock");
            default:
                return new Exception("Exception while getting product details");
        }
    }
}
