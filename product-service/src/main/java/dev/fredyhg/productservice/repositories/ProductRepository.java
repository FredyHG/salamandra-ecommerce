package dev.fredyhg.productservice.repositories;

import dev.fredyhg.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByName(String name);
    Optional<Product> findByCode(String code);
}
