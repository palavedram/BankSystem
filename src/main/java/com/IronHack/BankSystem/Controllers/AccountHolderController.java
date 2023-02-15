package com.IronHack.BankSystem.Controllers;

import com.IronHack.BankSystem.Services.users.imp.AccountHolderServiceImplement;
import com.IronHack.BankSystem.models.DTOs.AccountHolderDTO;
import com.IronHack.BankSystem.models.users.AccountHolder;
import com.IronHack.BankSystem.repositories.users.AccountHolderRepository;
import com.IronHack.BankSystem.repositories.users.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountHolderController {

    @Autowired
    AccountHolderServiceImplement accountHolderServiceImplement;


    //CRUD sobre la entidad AccountHolder

    //Buscar todos los AccountHolders
    @GetMapping("/accountHolder")
    public List<AccountHolder>findAll(){
        return accountHolderServiceImplement.findAll();
    }

    //Buscar AccountHolder por ID
    @GetMapping("/accountHolder/{id}")
    public AccountHolder findById(@PathVariable Integer id){
        return accountHolderServiceImplement.findById(id);
    }

    //Crear un AccountHolder
    @PostMapping("/accountHolder")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountHolder create (@RequestBody AccountHolderDTO accountHolderDTO){
        return accountHolderServiceImplement.create(accountHolderDTO);

    }

    //Actualizar un AccountHolder
    @PutMapping("/accountHolder")
    public AccountHolder update(@RequestParam AccountHolder accountHolder){
        return accountHolderServiceImplement.update(accountHolder);
    }
    //Borrar un AccountHolder
    @DeleteMapping("/accountHolder/{id}")
    public AccountHolder delete(@PathVariable Integer id){
        return accountHolderServiceImplement.delete(id);
    }



}
