package com.IronHack.BankSystem.models.accounts;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor

@Entity
public class CheckingAccount extends Account {

    private BigDecimal minimumBalance = BigDecimal.valueOf(250);
    private BigDecimal monthlyMaintenanceFee = BigDecimal.valueOf(-12);


    public void applyPenaltyFee() {
        BigDecimal balance = getBalance();
        if (balance.compareTo(minimumBalance) < 0) {
            BigDecimal penaltyBalance = balance.add(PENALTY_FEE);
            setBalance(penaltyBalance);
        }
    }

    public void applyMantenanceFee() {
        BigDecimal balance = getBalance();
        BigDecimal newBalance = balance.add(monthlyMaintenanceFee);
        setBalance(newBalance);


    }
}