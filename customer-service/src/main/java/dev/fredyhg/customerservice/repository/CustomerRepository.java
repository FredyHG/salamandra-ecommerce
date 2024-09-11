package dev.fredyhg.customerservice.repository;

import dev.fredyhg.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByCpf(String cpf);
}