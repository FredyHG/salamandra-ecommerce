package dev.fredyhg.productservice.configs;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockFeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new StockErrorDecoder();
    }
}
