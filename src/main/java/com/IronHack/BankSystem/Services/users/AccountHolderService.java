package com.IronHack.BankSystem.Services.users;

import com.IronHack.BankSystem.Services.users.imp.AccountHolderServiceImplement;
import com.IronHack.BankSystem.models.users.Address;
import com.IronHack.BankSystem.models.DTOs.AccountHolderDTO;

import com.IronHack.BankSystem.models.users.AccountHolder;
import com.IronHack.BankSystem.repositories.users.AccountHolderRepository;
import com.IronHack.BankSystem.repositories.users.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Transactional
@Service
public class AccountHolderService implements AccountHolderServiceImplement {

    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AddressRepository addressRepository;


    public List<AccountHolder> findAll() {
        return accountHolderRepository.findAll();
    }


    public AccountHolder create(AccountHolderDTO accountHolderDTO) {
        //create user
        AccountHolder newAccountHolder = new AccountHolder();
            newAccountHolder.setFirstName(accountHolderDTO.getFirstName());//String
            newAccountHolder.setLastName(accountHolderDTO.getLastName());//String
            newAccountHolder.setEmail(accountHolderDTO.getEmail());//String
            newAccountHolder.setDateOfBirth(accountHolderDTO.getDateOfBirth());//Localdate
        accountHolderRepository.save(newAccountHolder);

        //create address
        Address newAddress = new Address();
            newAddress.setStreet(accountHolderDTO.getStreet());//String
            newAddress.setCity(accountHolderDTO.getCity());//String
            newAddress.setCountry(accountHolderDTO.getCountry());//String
            newAddress.setZipCode(accountHolderDTO.getZipCode());//String
        addressRepository.save(newAddress);
        newAccountHolder.setPrimaryAddress(newAddress);
        return newAccountHolder;

/*

        //create account
        Account newAccount;
        LocalDate now = LocalDate.now();
        LocalDate birthDate = accountHolderDTO.getDateOfBirth();
        Period edad = Period.between(birthDate, now);

        switch (accountHolderDTO.getAccountType().toUpperCase()) {
            case "SAVINGS":
                newAccount = new Savings();
                savingsRepository.save((Savings) newAccount);
                break;
            case "CHECKING":
                if (edad.getYears() >= 18) {
                    // La persona es mayor de edad
                    newAccount = new CheckingAccount();
                    checkingRepository.save((CheckingAccount)newAccount);
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
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid account type");
        }
        accountRepository.save(newAccount);

        newAccount.setBalance(BigDecimal.valueOf(accountHolderDTO.getBalance()));
        newAccount.setSecretKey(accountHolderDTO.getSecretKey());
        newAccount.setPrimaryOwner(newAccountHolder);
        newAccount.setStatus(Status.ACTIVE);
        newAccount.setAccountType(AccountType.valueOf(accountHolderDTO.getAccountType().toUpperCase()));
        newAccount.setAccountHolder(newAccountHolder);
        accountRepository.save(newAccount);

        List<Account> accounts = new ArrayList<>();
        accounts.add(newAccount);

        newAccountHolder.setAccounts(accounts);
        newAccountHolder.setPrimaryAddress(newAddress);
        return newAccountHolder;
*/

        /*// Busca la PrimaryAddress usando el AddressId especificando en la DTO
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
        }*/

        //Assignarle una cuenta
/*        List<Account> accounts = new ArrayList<>();

            Account account = accountRepository.findById(accountHolderDTO.getAccounts())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
            accounts.add(account);

        newAccountHolder.setAccounts(accounts);*/


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
