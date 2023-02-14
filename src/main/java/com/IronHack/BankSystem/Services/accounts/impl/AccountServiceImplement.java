package com.IronHack.BankSystem.Services.accounts.impl;

import com.IronHack.BankSystem.models.DTOs.AccountDTO;
import com.IronHack.BankSystem.models.accounts.Account;
import com.IronHack.BankSystem.models.accounts.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface AccountServiceImplement {
    List<Account> findAll();

    Account findById(Integer id);

    Account createAccount(AccountDTO accountDTO);

}
