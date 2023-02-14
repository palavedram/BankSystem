package com.IronHack.BankSystem.models.DTOs;

import com.IronHack.BankSystem.models.accounts.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionDTO {


    private Integer transactionId;
    private BigDecimal amount;
    private LocalDateTime date;
    private Integer fromAccountId;
    private Account toAccountId;



}
