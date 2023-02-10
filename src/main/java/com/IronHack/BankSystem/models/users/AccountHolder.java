package com.IronHack.BankSystem.models.users;

import com.IronHack.BankSystem.models.accounts.Account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
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

    @OneToOne
    private Address primaryAddress ;
    @OneToOne
    private Address mailingAddress ;

    @OneToMany(mappedBy="accountHolder")
    @JsonIgnore
    private List<Account> accounts = new ArrayList<>();

}

