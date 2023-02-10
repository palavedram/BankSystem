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
import java.time.LocalDate;
import java.time.Period;
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


    public Account createAccount(Integer accountHolderId, AccountDTO accountDTO) {

        //find the accountholder
        AccountHolder accountHolderDB = accountHolderRepository.findById(accountHolderId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "AccountHolder not found"));

        //create account with the account holder
        Account newAccount;
            LocalDate now = LocalDate.now();
            LocalDate birthDate = accountHolderDB.getDateOfBirth();
            Period edad = Period.between(birthDate, now);

        switch (accountDTO.getAccountType().toUpperCase()) {
            case "SAVINGS":
                newAccount = new Savings();
                savingsRepository.save((Savings) newAccount);
                break;
            case "CHECKING":
                if (edad.getYears() >= 18) {
                    // La persona es mayor de edad
                    newAccount = new CheckingAccount();
                    checkingRepository.save((CheckingAccount) newAccount);
                } else {
                    // La persona es menor de edad
                    newAccount = new StudentChecking();
                    studentCheckingRepository.save((StudentChecking) newAccount);
                }
                break;
            case "CREDIT":
                newAccount = new CreditCardAccount();
                creditCardRepository.save((CreditCardAccount) newAccount);
                break;
            default:
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid account type");
        }
        newAccount.setBalance(BigDecimal.valueOf(accountDTO.getBalance()));
        newAccount.setSecretKey(accountDTO.getSecretKey());
        newAccount.setPrimaryOwner(accountHolderDB.getFirstName()+" "+accountHolderDB.getLastName());
        newAccount.setAccountHolder(accountHolderDB);
        newAccount.setStatus(Status.ACTIVE);
        newAccount.setAccountType(AcountType.valueOf(accountDTO.getAccountType().toUpperCase()));

        return newAccount;

    }
}
        /*// Busca la PrimaryAddress usando el AddressId especificando en la DTO
        Integer id = accountHolderDTO.getPrimaryAddress();
        Address addressDB = addressRepository.findById(Id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Address not found"));
        newAccountHolder.setPrimaryAddress(addressDB);

        //Busca la MailingAddress
        if(accountHolderDTO.getMailingAddress() == null){
            newAccountHolder.setMailingAddress(addressDB);
        }else{
            newAccountHolder.setMailingAddress(addressRepository.findById(accountHolderDTO.getMailingAddress()).orElseThrow(()->
                    new ResponseStatusException(HttpStatus.NOT_FOUND,"Address not found")));
        }*/

        //Assignarle una cuenta
/*        List<Account> accounts = new ArrayList<>();

            Account account = accountRepository.findById(accountHolderDTO.getAccounts())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
            accounts.add(account);

        newAccountHolder.setAccounts(accounts);*/








