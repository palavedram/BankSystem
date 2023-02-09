package com.IronHack.BankSystem.Services.users;

import com.IronHack.BankSystem.Services.users.imp.AccountHolderServiceImplement;
import com.IronHack.BankSystem.models.Address;
import com.IronHack.BankSystem.models.DTOs.AccountHolderDTO;
import com.IronHack.BankSystem.models.accounts.Account;
import com.IronHack.BankSystem.models.users.AccountHolder;
import com.IronHack.BankSystem.repositories.accounts.AccountRepository;
import com.IronHack.BankSystem.repositories.accounts.SavingsRepository;
import com.IronHack.BankSystem.repositories.users.AccountHolderRepository;
import com.IronHack.BankSystem.repositories.users.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class AccountHolderService implements AccountHolderServiceImplement {

    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AccountRepository accountRepository;



    public List<AccountHolder> findAll() {
        return accountHolderRepository.findAll();
    }


    public AccountHolder create(AccountHolderDTO accountHolderDTO) {
        AccountHolder newAccountHolder = new AccountHolder();
        newAccountHolder.setFirstName(accountHolderDTO.getFirstName());
        newAccountHolder.setLastName(accountHolderDTO.getLastName());
        newAccountHolder.setEmail(accountHolderDTO.getEmail());
        newAccountHolder.setDateOfBirth(accountHolderDTO.getDateOfBirth());

        // Busca la PrimaryAddress usando el AddressId especificando en la DTO
        Integer Id = accountHolderDTO.getPrimaryAddress();
        Address addressDB = addressRepository.findById(Id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Address not found"));
        newAccountHolder.setPrimaryAddress(addressDB);

        //Busca la MailingAddress
        if(accountHolderDTO.getMailingAddress() == null){
            newAccountHolder.setMailingAddress(addressDB);
        }else{
            newAccountHolder.setMailingAddress(addressRepository.findById(accountHolderDTO.getMailingAddress()).orElseThrow(()->
                    new ResponseStatusException(HttpStatus.NOT_FOUND,"Address not found")));
        }

        //Assignarle una cuenta
        List<Account> accounts = new ArrayList<>();

            Account account = accountRepository.findById(accountHolderDTO.getAccounts())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
            accounts.add(account);

        newAccountHolder.setAccounts(accounts);


        accountHolderRepository.save(newAccountHolder);
        return newAccountHolder;
    }


    public AccountHolder update(AccountHolder accountHolder) {
        return null;
    }


    public AccountHolder delete(Integer id) {
        return null;
    }


    public AccountHolder findById(Integer id) {
        return accountHolderRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Address not found"));
    }
}
