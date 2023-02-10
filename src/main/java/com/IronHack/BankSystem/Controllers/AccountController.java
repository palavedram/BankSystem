package com.IronHack.BankSystem.Controllers;


import com.IronHack.BankSystem.Services.accounts.impl.AccountServiceImplement;
import com.IronHack.BankSystem.models.DTOs.AccountDTO;
import com.IronHack.BankSystem.models.accounts.Account;
import com.IronHack.BankSystem.models.accounts.Savings;
import com.IronHack.BankSystem.repositories.accounts.*;
import com.IronHack.BankSystem.repositories.users.AccountHolderRepository;
import com.IronHack.BankSystem.repositories.users.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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


    @PostMapping("/account/{accountHolderId}")
    public Account create(@PathVariable Integer accountHolderId, @RequestBody AccountDTO accountDTO) {
        return accountServiceImplement.createAccount(accountHolderId , accountDTO);
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
