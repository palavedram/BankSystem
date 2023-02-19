package com.IronHack.BankSystem.Controllers;

import com.IronHack.BankSystem.Services.users.imp.AccountHolderServiceImplement;
import com.IronHack.BankSystem.models.accounts.Account;
import com.IronHack.BankSystem.models.accounts.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accountHolder")

public class AccountHolderController {

    @Autowired
    AccountHolderServiceImplement accountHolderServiceImplement;


    @GetMapping("/accounts")
    public List<Account> findAHaccounts(Authentication autentication){
        System.out.println(autentication.getPrincipal());
        return accountHolderServiceImplement.find(autentication);
    }

    @PatchMapping("/operate")
    public Account operate (Authentication authentication,
                            @RequestParam Integer id,
                            @RequestParam String type,
                            @RequestParam Integer amount){
        System.out.println(authentication.getPrincipal());
        return accountHolderServiceImplement.depositWithdraw(authentication, id, type ,amount);
    }

    @PostMapping("/transaction")
    public Transaction createTransactionbyOwner(
            Authentication authentication,
            @RequestParam Integer senderAccountId,
            @RequestParam Integer receiverAccountId,
            @RequestParam BigDecimal amount){
        return accountHolderServiceImplement.createTransactionbyOwner(authentication,senderAccountId,receiverAccountId,amount);
    }
    @PostMapping("/transactionBetweenOwenedAccounts")
    public Transaction createTransactionToSameOwnerAccounts(
            Authentication authentication,
            @RequestParam Integer senderAccountId,
            @RequestParam Integer receiverAccountId,
            @RequestParam BigDecimal amount){
        return accountHolderServiceImplement.createTransactionToSameOwnerAccounts(authentication,senderAccountId,receiverAccountId,amount);
    }

}
