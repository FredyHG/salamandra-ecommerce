package dev.fredyhg.orderservice.common.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class CustomerResponse {
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
    private List<AddressResponse> addresses;
}
