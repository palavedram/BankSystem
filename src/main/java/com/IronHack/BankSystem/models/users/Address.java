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

    @OneToOne(mappedBy="primaryAddress")
    private AccountHolder accountHolder;
    @OneToOne(mappedBy="mailingAddress")
    private AccountHolder mailingAddress;


}




