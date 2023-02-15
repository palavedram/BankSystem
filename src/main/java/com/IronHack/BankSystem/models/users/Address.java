package com.IronHack.BankSystem.models.users;

import com.IronHack.BankSystem.models.users.AccountHolder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer addressId;
    private String street;
    private String city;
    private String country;
    private String zipCode;

    @OneToOne
    private AccountHolder accountHolder;

    public Address(String street, String city, String country, String zipCode) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
    }
}




