package com.IronHack.BankSystem.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreditDTO {

    private Integer accountHolderId;
    private Integer balance;
    private String secretKey;


    //Credit attributes

    private Integer creditLimit = 100;
    private Double interestRate = 0.2;


}
