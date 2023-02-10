package com.IronHack.BankSystem.models.accounts;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor

@Entity
public class StudentChecking extends Account {
    private float minimumBalance = 0;
    private float monthlyMaintenanceFee = 0;
}
