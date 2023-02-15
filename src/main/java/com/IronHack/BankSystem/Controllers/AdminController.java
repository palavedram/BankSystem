
package com.IronHack.BankSystem.Controllers;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")

public class AdminController {

    @Autowired
    AdminServiceImplement adminServiceImplement;

    /**
     *
     * CREAR
     */

    //Crear un Savings
    @PostMapping("/savings")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Savings createSavings (@RequestBody SavingsDTO savingsDTO){
        return adminServiceImplement.createSavings(savingsDTO);
    }

    //Crear un Checking
    @PostMapping("/checking")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CheckingAccount createSavings (@RequestBody CheckingDTO checkingDTO){
        return adminServiceImplement.createChecking(checkingDTO);
    }

    //Crear un Credit
    @PostMapping("/credit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CreditCardAccount createSavings (@RequestBody CreditDTO creditDTO){
        return adminServiceImplement.createCredit(creditDTO);
    }
    //Crear un AccountHolder
    @PostMapping("/accountHolder")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountHolder create (@RequestBody AccountHolderDTO accountHolderDTO){
        return adminServiceImplement.createAccountHolder(accountHolderDTO);
    }

    /**
     *
     * BUSCAR
     *
     */

    //Buscar AccountHolder por ID
    @GetMapping("/accountHolders/{id}")
    public AccountHolder findAccountHolderById(@PathVariable Integer id){
        return adminServiceImplement.findAccountHolderById(id);
    }
    //Buscar todos los accountsHolder
    @GetMapping("/accountHolders")
    public List<AccountHolder> findAllAccountsHolder(){
        return adminServiceImplement.findAllAccountHolder();
    }
    //Buscar Account por ID
    @GetMapping("/accounts/{id}")
    public Account findAccountById(@PathVariable Integer id){
        return adminServiceImplement.findAccountById(id);
    }
    //Buscar todos los accounts
    @GetMapping("/accounts")
    public List<Account> findAllAccounts(){
        return adminServiceImplement.findAllAccounts();
    }
    //Buscar savings
    @GetMapping("/savings")
    public List<Savings> findAllSavings(){
        return adminServiceImplement.findAllSavings();
    }
    //Buscar Checkings
    @GetMapping("/checking")
    public List<CheckingAccount> findAllCheckings(){
        return adminServiceImplement.findAllCheckings();
    }
    //Buscar Credit
    @GetMapping("/credit")
    public List<CreditCardAccount> findAllCredit(){
        return adminServiceImplement.findAllCredit();
    }



    /**
     *   ACTUALIZAR
     */
    //Actualizar un AccountHolder
    @PutMapping("/accountHolder/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountHolder updateAccountHolder(@PathVariable Integer id , @RequestBody AccountHolder accountHolder){
        return adminServiceImplement.updateAccountHolder(id, accountHolder);
    }

    //Acctualizar accountBalance  /api/admin/updateBalance
    @PatchMapping("/updateBalance")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Account updateAccountBalance(@RequestParam Integer accountId, @RequestParam Double newBalance){
        return adminServiceImplement.UpdateAccountBalance(accountId,newBalance);
    }


    //Actualizar  Checking
    @PutMapping("/checking/{id}")
    public CheckingAccount updateChecking(@PathVariable Integer id , @RequestBody CheckingAccount checkingAccount){
        return adminServiceImplement.updateChecking(id, checkingAccount);
    }
    //Actualizar  Saving
    @PutMapping("/saving/{id}")
    public Savings updateSaving(@PathVariable Integer id , @RequestBody Savings savings){
        return adminServiceImplement.updateSaving(id, savings);
    }
    //Actualizar Credit
    @PutMapping("/credit/{id}")
    public CreditCardAccount updateCredit(@RequestParam Integer id , @RequestBody CreditCardAccount creditCardAccount){
        return adminServiceImplement.updateCredit(id, creditCardAccount);
    }

    /**
     *
     * BORRAR
     *
     */
    //Borrar un AccountHolder
    @DeleteMapping("/accountHolder/{id}")
    public AccountHolder deleteAccountHolder(@RequestParam Integer id){
        return adminServiceImplement.deleteAccountHolder(id);
    }
    //Borrar un Account
    @DeleteMapping("/account")
    public Account deleteAccount(@RequestParam Integer id){
        return adminServiceImplement.deleteAccount(id);
    }



}


