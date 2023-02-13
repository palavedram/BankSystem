package com.IronHack.BankSystem.models.accounts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor

@Entity
public class StudentChecking extends CheckingAccount {
    private Integer minimumBalance = 0;
    private Integer monthlyMaintenanceFee = 0;
}
