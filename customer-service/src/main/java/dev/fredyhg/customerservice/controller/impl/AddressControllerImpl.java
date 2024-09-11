package dev.fredyhg.customerservice.controller.impl;

import dev.fredyhg.customerservice.controller.AddressController;
import dev.fredyhg.customerservice.controller.request.AddressPostRequest;
import dev.fredyhg.customerservice.controller.response.ResponseMessage;
import dev.fredyhg.customerservice.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/v1/customer/address")
@RequiredArgsConstructor
public class AddressControllerImpl implements AddressController {

    private final AddressService addressService;

    @PostMapping("/add")
    @Override
    public ResponseEntity<ResponseMessage> addAddress(@RequestBody AddressPostRequest addressRequest) {
        log.info("Receive request to add a address with zip: {} in customer with cpf: {}", addressRequest.getZip(), addressRequest.getCustomerCPF());

        addressService.addAddress(addressRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseMessage.builder()
                        .code(201)
                        .message("Successfully added address")
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}
