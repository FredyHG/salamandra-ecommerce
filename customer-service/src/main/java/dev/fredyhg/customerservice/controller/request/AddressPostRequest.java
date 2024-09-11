package dev.fredyhg.customerservice.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AddressPostRequest {
    @JsonProperty("customer_cpf")
    private String customerCPF;
    private String street;
    private String city;
    private String state;
    private String zip;
}
