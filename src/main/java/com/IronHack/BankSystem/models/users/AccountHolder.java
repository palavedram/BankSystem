package com.IronHack.BankSystem.models.users;

import com.IronHack.BankSystem.models.accounts.Account;

import com.IronHack.BankSystem.models.security.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor


@Entity
public class AccountHolder extends User {

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


}

