package dev.fredyhg.stockservice.repositories;

import dev.fredyhg.stockservice.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, UUID> {
    Optional<Stock> findByProductCode(String code);
}
