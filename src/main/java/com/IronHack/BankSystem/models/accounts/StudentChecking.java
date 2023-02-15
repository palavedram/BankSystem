package com.IronHack.BankSystem.models.accounts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
@NoArgsConstructor

@Entity
public class StudentChecking extends CheckingAccount {
    private BigDecimal minimumBalance = BigDecimal.valueOf(0);
    private BigDecimal monthlyMaintenanceFee = BigDecimal.valueOf(0);
}
