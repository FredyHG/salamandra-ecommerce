package dev.fredyhg.customerservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phone;

    private String cpf;

    @Size(max = 2)
    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    private List<Address> addresses;

    protected Customer() {}

    public Customer(String cpf, String phone, String firstName, String lastName, String email) {
        this.cpf = cpf;
        this.phone = phone;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;

        this.addresses = new ArrayList<>();
    }
}
