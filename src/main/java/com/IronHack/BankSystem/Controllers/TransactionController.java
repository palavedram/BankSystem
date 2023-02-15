package com.IronHack.BankSystem.Controllers;

import com.IronHack.BankSystem.Services.accounts.impl.AccountServiceImplement;
import com.IronHack.BankSystem.Services.accounts.impl.TransactionServiceImplement;
import com.IronHack.BankSystem.models.accounts.Account;
import com.IronHack.BankSystem.models.accounts.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    TransactionServiceImplement transactionServiceImplement;

    @PostMapping("/transaction")
    public Transaction create(
            @RequestParam Integer senderId,
            @RequestParam Integer receiverId,
            @RequestParam BigDecimal amount){
       return transactionServiceImplement.create(senderId,receiverId,amount);
    }

    @GetMapping("/transaction")
    public List<Transaction>findAllTransaction(){
        return transactionServiceImplement.findAllTransaction();
    }


}
