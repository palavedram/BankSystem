package com.IronHack.BankSystem.models;

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



}




