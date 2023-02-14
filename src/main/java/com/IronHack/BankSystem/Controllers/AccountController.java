package com.IronHack.BankSystem.Controllers;

import com.IronHack.BankSystem.Services.accounts.impl.AccountServiceImplement;
import com.IronHack.BankSystem.models.DTOs.AccountDTO;
import com.IronHack.BankSystem.models.accounts.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    AccountServiceImplement accountServiceImplement;
    //CRUD sobre la entidad account

    @GetMapping("/account")
    public List<Account> findAll() {
        return accountServiceImplement.findAll();
    }
    @GetMapping("/account/{id}")
    public Account findById(@PathVariable Integer id) {
        return accountServiceImplement.findById(id);
    }


    @PostMapping("/account")
    public Account create(@RequestBody AccountDTO accountDTO) {
        return accountServiceImplement.createAccount(accountDTO);
    }

    @PutMapping("/account")
    public Account update(@RequestBody Account account) {
        return null;
    }

    @DeleteMapping("/account/{id}")
    public Account delete(@RequestBody Integer id) {
        return null;
    }
}
