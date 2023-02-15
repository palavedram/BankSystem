package com.IronHack.BankSystem.repositories.accounts;

import com.IronHack.BankSystem.models.accounts.Account;
import com.IronHack.BankSystem.models.accounts.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    List<Transaction> findBySenderId(Account sender);
}
