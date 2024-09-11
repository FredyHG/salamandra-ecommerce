package dev.fredyhg.customerservice.controller;

import dev.fredyhg.customerservice.controller.request.AddressPostRequest;
import dev.fredyhg.customerservice.controller.response.ResponseMessage;
import org.springframework.http.ResponseEntity;

public interface AddressController {
    ResponseEntity<ResponseMessage> addAddress(AddressPostRequest addressRequest);
}
