package com.IronHack.BankSystem.Services.accounts;


import com.IronHack.BankSystem.Services.accounts.impl.AccountServiceImplement;
import com.IronHack.BankSystem.models.DTOs.AccountDTO;

import com.IronHack.BankSystem.models.Enum.AcountType;
import com.IronHack.BankSystem.models.Enum.Status;
import com.IronHack.BankSystem.models.accounts.*;
import com.IronHack.BankSystem.models.users.AccountHolder;
import com.IronHack.BankSystem.repositories.accounts.*;
import com.IronHack.BankSystem.repositories.users.AccountHolderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

import java.util.List;

@Transactional
@Service
public class AccountServices implements AccountServiceImplement {

    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;


    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findById(Integer id) {
        return accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
    }


    public Account createAccount(AccountDTO accountDTO) {

        //find the accountholder
        AccountHolder accountHolderDB = accountHolderRepository.findById(accountDTO.getAccountHolderId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "AccountHolder not found"));

        //create account with the account holder
        Account newAccount;

        switch (accountDTO.getAccountType().toUpperCase()) {
            case "SAVINGS":
                newAccount = new Savings();
                newAccount.setAccountType(AcountType.SAVINGS);
                savingsRepository.save((Savings) newAccount);
                break;
            case "CHECKING":
                if (accountHolderDB.getEdad() >= 25) {
                    // La persona es mayor de edad
                    newAccount = new CheckingAccount();
                    newAccount.setAccountType(AcountType.CHECKING);
                    checkingRepository.save((CheckingAccount) newAccount);
                } else {
                    // La persona es menor de edad
                    newAccount = new StudentChecking();
                    newAccount.setAccountType(AcountType.STUDENT);
                    studentCheckingRepository.save((StudentChecking) newAccount);
                }
                // Establecer el balance después de crear la instancia de CheckingAccount
                if (accountDTO.getBalance() == null) {
                    newAccount.setBalance(((CheckingAccount) newAccount).getMinimumBalance());
                } else {
                    newAccount.setBalance(BigDecimal.valueOf(accountDTO.getBalance()));
                }
                break;

            case "CREDIT":
                newAccount = new CreditCardAccount();
                newAccount.setAccountType(AcountType.CREDIT);
                creditCardRepository.save((CreditCardAccount) newAccount);
                break;
            default:
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid account type");
        }


        if (accountDTO.getBalance() != null) {
            newAccount.setBalance(BigDecimal.valueOf(accountDTO.getBalance()));
        }

            newAccount.setSecretKey(accountDTO.getSecretKey());
            newAccount.setPrimaryOwner(accountHolderDB.getFirstName() + " " + accountHolderDB.getLastName());
            newAccount.setAccountHolder(accountHolderDB);
            newAccount.setStatus(Status.ACTIVE);

            return newAccount;

        }

    }







