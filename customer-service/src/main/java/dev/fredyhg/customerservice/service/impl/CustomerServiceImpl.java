package dev.fredyhg.customerservice.service.impl;

import dev.fredyhg.customerservice.controller.request.CustomerPostRequest;
import dev.fredyhg.customerservice.controller.response.CustomerGetResponse;
import dev.fredyhg.customerservice.mapper.CustomerMapper;
import dev.fredyhg.customerservice.model.Customer;
import dev.fredyhg.customerservice.repository.CustomerRepository;
import dev.fredyhg.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void registerCustomer(CustomerPostRequest customerRequest) {

        log.info("Registering new customer with CPF: {}", customerRequest.getCpf());

        ensureCustomerNotExistsByCPF(customerRequest.getCpf());
        ensureCustomerNotExistsByEmail(customerRequest.getEmail());

        log.info("Mapping customer post request to customer model");
        Customer customer = CustomerMapper.postRequestToModel(customerRequest);

        log.info("Saving customer with CPF: {}", customer.getCpf());
        customerRepository.save(customer);
    }

    @Override
    public Customer ensureCustomerExistsByEmail(String email) {
        log.info("Checking if customer with email: {}", email);

        return findCustomerByEmail(email).orElseThrow(() -> new RuntimeException("Customer not found with email: " + email));
    }

    @Override
    public Customer ensureCustomerExistsByCPF(String cpf) {
        log.info("Checking if customer with cpf: {}", cpf);

        return findCustomerByCPF(cpf).orElseThrow(() -> new RuntimeException("Customer not found with CPF: " + cpf));
    }

    @Override
    public Customer ensureCustomerExistsById(String id) {
        log.info("Checking if customer with id: {}", id);

        return findCustomerById(id).orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    @Override
    public void ensureCustomerNotExistsByEmail(String email) {
        log.info("Ensure customer not exist with email: {}", email);

        findCustomerByEmail(email).ifPresent(customer -> {
            throw new RuntimeException("Customer already exists with email: " + customer.getEmail());
        });
    }

    @Override
    public void ensureCustomerNotExistsByCPF(String cpf) {
        log.info("Ensure customer not exist with cpf: {}", cpf);

        findCustomerByCPF(cpf).ifPresent(customer -> {
            throw new RuntimeException("Customer already exists with email: " + customer.getEmail());
        });
    }

    @Override
    public CustomerGetResponse findGetCustomerById(String id) {
        Customer customer = ensureCustomerExistsById(id);

        return CustomerMapper.customerToResponse(customer);
    }


    private Optional<Customer> findCustomerByEmail(String email) {
        log.info("Find optional customer by email: {}", email);

        return customerRepository.findByEmail(email);
    }

    private Optional<Customer> findCustomerByCPF(String cpf) {
        log.info("Find optional customer by cpf: {}", cpf);

        return customerRepository.findByCpf(cpf);
    }

    private Optional<Customer> findCustomerById(String id) {
        log.info("Find optional customer by id: {}", id);

        return customerRepository.findById(UUID.fromString(id));
    }
}
