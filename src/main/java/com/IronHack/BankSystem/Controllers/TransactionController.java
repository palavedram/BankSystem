package com.IronHack.BankSystem.Controllers;

import com.IronHack.BankSystem.Services.accounts.impl.AccountServiceImplement;
import com.IronHack.BankSystem.Services.accounts.impl.TransactionServiceImplement;
import com.IronHack.BankSystem.models.accounts.Account;
import com.IronHack.BankSystem.models.accounts.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    TransactionServiceImplement transactionServiceImplement;

    @PostMapping("/transaction")
    public Transaction create(
            @RequestParam Integer fromAccountId,
            @RequestParam Integer toAccountId,
            @RequestParam BigDecimal amount){
       return transactionServiceImplement.create(fromAccountId,toAccountId,amount);
    }


}
