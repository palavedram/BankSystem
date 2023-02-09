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
public class Savings extends Account {
    private float interestRate = 0.0025f;
    // Getters and setters ...
}

