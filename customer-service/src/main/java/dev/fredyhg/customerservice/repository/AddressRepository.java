package dev.fredyhg.customerservice.repository;

import dev.fredyhg.customerservice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
