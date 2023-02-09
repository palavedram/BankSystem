package com.IronHack.BankSystem.Services.accounts.impl;

import com.IronHack.BankSystem.models.accounts.Savings;

import java.util.List;

public interface SavingsServiceImplement {
    List<Savings> findAll();

    Savings create(Savings savings);

    Savings update(Savings savings);

    Savings delete(Integer id);

    Savings findById(Integer id);
}
