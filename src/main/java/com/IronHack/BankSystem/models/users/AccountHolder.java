package com.IronHack.BankSystem.models.users;

import com.IronHack.BankSystem.models.accounts.Account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Setter
@Getter


@Entity
public class AccountHolder {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private Integer edad;

    @OneToOne
    @JsonIgnore
    private Address primaryAddress ;
    @OneToOne
    @JsonIgnore
    private Address mailingAddress ;

    @OneToMany(mappedBy="accountHolder")
    @JsonIgnore
    private List<Account> accounts = new ArrayList<>();

    public AccountHolder(String firstName, String lastName, String email, LocalDate dateOfBirth, Integer edad, Address primaryAddress, Address mailingAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.edad = edad;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }
}

