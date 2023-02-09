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
    private float creditLimit = 100;
    private float interestRate = 0.2f;
    // Getters and setters ...






}