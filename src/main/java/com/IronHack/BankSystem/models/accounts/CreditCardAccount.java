package com.IronHack.BankSystem.models.accounts;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor

@Entity
public class CreditCardAccount extends Account {
    private Integer creditLimit = 100;
    private Double interestRate = 0.2;

}