package dev.fredyhg.customerservice.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.fredyhg.customerservice.model.Address;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class CustomerGetResponse {
    private UUID id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("cpf")
    private String cpf;
    @JsonProperty
    private List<Address> addresses;

    protected CustomerGetResponse() {}

    public CustomerGetResponse(UUID id, String cpf, String phone, String lastName, String email, String firstName, List<Address> addresses) {
        this.id = id;
        this.cpf = cpf;
        this.phone = phone;
        this.lastName = lastName;
        this.email = email;
        this.firstName = firstName;
        this.addresses = addresses;
    }
}
