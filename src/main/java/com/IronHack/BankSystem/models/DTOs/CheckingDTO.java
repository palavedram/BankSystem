package com.IronHack.BankSystem.models.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckingDTO {

    private Integer accountHolderId;
    private String secretKey;

    private Double monthlyMaintenanceFee;
    private Integer minimumBalance;



}
