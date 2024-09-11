package dev.fredyhg.customerservice.service.impl;

import dev.fredyhg.customerservice.controller.request.AddressPostRequest;
import dev.fredyhg.customerservice.mapper.AddressMapper;
import dev.fredyhg.customerservice.model.Address;
import dev.fredyhg.customerservice.model.Customer;
import dev.fredyhg.customerservice.repository.AddressRepository;
import dev.fredyhg.customerservice.service.AddressService;
import dev.fredyhg.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final CustomerService customerService;
    private final AddressRepository addressRepository;

    @Override
    public void addAddress(AddressPostRequest addressRequest) {

        Customer customer = customerService.ensureCustomerExistsByCPF(addressRequest.getCustomerCPF());

        if(customer.getAddresses().size() > 4) throw new RuntimeException("Customer already has two address");

        Address address = AddressMapper.addressRequestToModel(addressRequest);

        address.setCustomer(customer);

        addressRepository.save(address);

    }
}
