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
public class CheckingAccount extends Account {
    private Integer minimumBalance = 250;
    private Integer monthlyMaintenanceFee = 12;

}