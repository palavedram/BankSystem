package com.IronHack.BankSystem.Services.accounts.impl;

import com.IronHack.BankSystem.models.accounts.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionServiceImplement {
    Transaction create(Integer senderId, Integer receiverId, BigDecimal amount);

    List<Transaction> findAllTransaction();
}
