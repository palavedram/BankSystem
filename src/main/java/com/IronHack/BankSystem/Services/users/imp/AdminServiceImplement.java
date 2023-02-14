package com.IronHack.BankSystem.Services.users.imp;

import com.IronHack.BankSystem.models.DTOs.AccountHolderDTO;
import com.IronHack.BankSystem.models.DTOs.CheckingDTO;
import com.IronHack.BankSystem.models.DTOs.CreditDTO;
import com.IronHack.BankSystem.models.DTOs.SavingsDTO;
import com.IronHack.BankSystem.models.accounts.Account;
import com.IronHack.BankSystem.models.accounts.CheckingAccount;
import com.IronHack.BankSystem.models.accounts.CreditCardAccount;
import com.IronHack.BankSystem.models.accounts.Savings;
import com.IronHack.BankSystem.models.users.AccountHolder;

import java.util.List;

public interface AdminServiceImplement {


    Savings createSavings(SavingsDTO savingsDTO);

    CheckingAccount createChecking(CheckingDTO checkingDTO);

    CreditCardAccount createCredit(CreditDTO creditDTO);

    AccountHolder createAccountHolder(AccountHolderDTO accountHolderDTO);

    AccountHolder updateAccountHolder(Integer id, AccountHolder accountHolder);

    CheckingAccount updateChecking(Integer id, CheckingAccount checkingAccount);

    Savings updateSaving(Integer id, Savings savings);

    CreditCardAccount updateCredit(Integer id, CreditCardAccount creditCardAccount);

    AccountHolder deleteAccountHolder(Integer id);

    Account deleteAccount(Integer id);

    AccountHolder findAccountHolderById(Integer id);

    List<AccountHolder> findAllAccountHolder();

    Account findAccountById(Integer id);

    List<Account> findAllAccounts();

    List<Savings> findAllSavings();

    List<CheckingAccount> findAllCheckings();

    List<CreditCardAccount> findAllCredit();
}
