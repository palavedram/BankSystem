package com.IronHack.BankSystem.models.DTOs;

import com.IronHack.BankSystem.models.users.AccountHolder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class AccountDTO {

    //Account attributes
    private Integer accountHolderId;

    private Integer balance;
    private String secretKey;

    private Integer minimumBalance;
    private Integer penaltyFee;
    private Integer monthlyMaintenanceFee;
    private String status;

    private String accountType;

}
