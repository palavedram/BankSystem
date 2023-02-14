package com.IronHack.BankSystem.Services.users;

import com.IronHack.BankSystem.Services.users.imp.AdminServiceImplement;
import com.IronHack.BankSystem.models.DTOs.AccountHolderDTO;
import com.IronHack.BankSystem.models.DTOs.CheckingDTO;
import com.IronHack.BankSystem.models.DTOs.CreditDTO;
import com.IronHack.BankSystem.models.DTOs.SavingsDTO;
import com.IronHack.BankSystem.models.accounts.Account;
import com.IronHack.BankSystem.models.accounts.CheckingAccount;
import com.IronHack.BankSystem.models.accounts.CreditCardAccount;
import com.IronHack.BankSystem.models.accounts.Savings;
import com.IronHack.BankSystem.models.users.AccountHolder;
import com.IronHack.BankSystem.models.users.Address;
import com.IronHack.BankSystem.repositories.accounts.*;
import com.IronHack.BankSystem.repositories.users.AccountHolderRepository;
import com.IronHack.BankSystem.repositories.users.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Transactional
@Service
public class AdminService implements AdminServiceImplement {
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
    @Autowired
    AddressRepository addressRepository;

    /**
     *
     * CREAR
     */

    public Savings createSavings(SavingsDTO savingsDTO) {
        return null;
    }


    public CheckingAccount createChecking(CheckingDTO checkingDTO) {
        return null;
    }


    public CreditCardAccount createCredit(CreditDTO creditDTO) {
        return null;
    }

    public AccountHolder createAccountHolder(AccountHolderDTO accountHolderDTO) {
        //create user
        AccountHolder newAccountHolder = new AccountHolder();
        LocalDate now = LocalDate.now();
        LocalDate birthDate = accountHolderDTO.getDateOfBirth();
        Period edad = Period.between(birthDate, now);

        newAccountHolder.setFirstName(accountHolderDTO.getFirstName());//String
        newAccountHolder.setLastName(accountHolderDTO.getLastName());//String
        newAccountHolder.setEmail(accountHolderDTO.getEmail());//String
        newAccountHolder.setDateOfBirth(accountHolderDTO.getDateOfBirth());//Localdate
        newAccountHolder.setEdad(edad.getYears());
        accountHolderRepository.save(newAccountHolder);

        //create address
        Address newAddress = new Address();
        newAddress.setStreet(accountHolderDTO.getStreet());//String
        newAddress.setCity(accountHolderDTO.getCity());//String
        newAddress.setCountry(accountHolderDTO.getCountry());//String
        newAddress.setZipCode(accountHolderDTO.getZipCode());//String
        newAddress.setAccountHolder(newAccountHolder);
        addressRepository.save(newAddress);
        newAccountHolder.setPrimaryAddress(newAddress);
        //newAccountHolder.setMailingAddress(newAddress);
        return newAccountHolder;
    }

    /**
     *
     * ACTUALIZAR
     */

    public AccountHolder updateAccountHolder(Integer id, AccountHolder accountHolder) {
        return null;
    }


    public CheckingAccount updateChecking(Integer id, CheckingAccount checkingAccount) {
        return null;
    }


    public Savings updateSaving(Integer id, Savings savings) {
        return null;
    }


    public CreditCardAccount updateCredit(Integer id, CreditCardAccount creditCardAccount) {
        return null;
    }

    /**
     *
     * BORRAR
     */
    public AccountHolder deleteAccountHolder(Integer id) {
        AccountHolder accountHolder = accountHolderRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"AccountHolder con "+ id+" no existe en la base de datos"));
        accountHolderRepository.delete(accountHolder);
        return accountHolder;
    }

    public Account deleteAccount(Integer id) {
        Account account = accountRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"La cuenta con "+ id+" no existe en la base de datos"));
        accountRepository.delete(account);

        return account;
    }


    /**
     * BUSCAR
     *
     */
    public AccountHolder findAccountHolderById(Integer id) {
        return accountHolderRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"AccountHolder not found"));
    }
    public List<AccountHolder> findAllAccountHolder() {
        return accountHolderRepository.findAll();
    }
    public Account findAccountById(Integer id) {
        return accountRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found"));
    }
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }


    public List<Savings> findAllSavings() {
        return savingsRepository.findAll();
    }


    public List<CheckingAccount> findAllCheckings() {
        return checkingRepository.findAll();
    }


    public List<CreditCardAccount> findAllCredit() {
        return creditCardRepository.findAll();
    }
}
