package dev.fredyhg.customerservice.service;

import dev.fredyhg.customerservice.controller.request.AddressPostRequest;
import dev.fredyhg.customerservice.model.Address;
import org.springframework.http.ResponseEntity;

public interface AddressService {
    void addAddress(AddressPostRequest address);
}
