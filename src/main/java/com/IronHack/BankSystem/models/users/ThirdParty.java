package com.IronHack.BankSystem.models.users;
import com.IronHack.BankSystem.models.accounts.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@Setter
@Getter

@Entity
public class ThirdParty extends User {

    private String hashedKey;

    @OneToMany(mappedBy="thirdParty")
    private List<Account> accounts;
    // Getters and setters ...
}
