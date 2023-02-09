package com.IronHack.BankSystem.Services.users.imp;

import com.IronHack.BankSystem.models.DTOs.AccountHolderDTO;
import com.IronHack.BankSystem.models.users.AccountHolder;

import java.util.List;

public interface AccountHolderServiceImplement {
    List<AccountHolder> findAll();

    AccountHolder create(AccountHolderDTO accountHolderDTO);

    AccountHolder update(AccountHolder accountHolder);

    AccountHolder delete(Integer id);

    AccountHolder findById(Integer id);
}
