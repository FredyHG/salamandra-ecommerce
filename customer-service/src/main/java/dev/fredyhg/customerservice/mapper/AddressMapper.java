package dev.fredyhg.customerservice.mapper;

import dev.fredyhg.customerservice.controller.request.AddressPostRequest;
import dev.fredyhg.customerservice.model.Address;

public class AddressMapper {
    public static Address addressRequestToModel(AddressPostRequest address) {
        return new Address(address.getStreet(), address.getCity(), address.getState(), address.getState());
    }
}
