package com.IronHack.BankSystem.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SavingsDTO {

    private Integer accountHolderId;
    private String secretKey;

    private Double interestRate;
    private Integer minimumBalance;



}
