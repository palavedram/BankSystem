package com.IronHack.BankSystem.Services.accounts.impl;

import com.IronHack.BankSystem.models.accounts.Transaction;

import java.math.BigDecimal;

public interface TransactionServiceImplement {
    Transaction create(Integer fromAccountId, Integer toAccountId, BigDecimal amount);
}
