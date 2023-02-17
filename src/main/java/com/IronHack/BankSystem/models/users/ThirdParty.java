package com.IronHack.BankSystem.models.users;

import com.IronHack.BankSystem.models.security.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@Setter
@Getter
@Entity
public class ThirdParty extends User {

    private String hashedKey;
}
