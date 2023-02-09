package com.IronHack.BankSystem.models.users;

import com.IronHack.BankSystem.models.accounts.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;


@NoArgsConstructor
@Setter
@Getter


@Entity
public class AccountHolder extends User {


    @OneToMany(mappedBy = "accountHolder") //@MappedBy que indica que la relación entre AccountHolder y Account se define en la clase Account.
    private List<Account> accounts;

}

