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
public class Savings extends Account {
    private Double interestRate = 0.0025;
    private BigDecimal minimumBalance = BigDecimal.valueOf(1000);

    public void applyPenaltyFee() {
        BigDecimal balance = getBalance();
        if (balance.compareTo(minimumBalance) < 0) {
            BigDecimal penaltyBalance = balance.subtract(PENALTY_FEE);
            setBalance(penaltyBalance);
        }
    }


}


