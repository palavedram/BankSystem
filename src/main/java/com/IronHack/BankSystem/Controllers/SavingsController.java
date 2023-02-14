package com.IronHack.BankSystem.Controllers;

import com.IronHack.BankSystem.Services.accounts.impl.SavingsServiceImplement;
import com.IronHack.BankSystem.models.accounts.Savings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SavingsController {
    @Autowired
    SavingsServiceImplement savingsServiceImplement;

    //CRUD sobre la entidad Savings

    //Buscar todos los Savings
    @GetMapping("/savings")
    public List<Savings> findAll(){
        return savingsServiceImplement.findAll();
    }

    //Buscar Ceunta Saving por ID

    @GetMapping("/savings/{id}")
    public Savings findById(@PathVariable Integer id){
        return savingsServiceImplement.findById(id);
    }


    //Crear un Savings
    @PostMapping("/savings")
    public Savings create (@RequestBody Savings savings){
        return savingsServiceImplement.create(savings);

    }

    //Borrar un Savings
    @DeleteMapping("/savings/{id}")
    public Savings delete(@PathVariable Integer id){
        return savingsServiceImplement.delete(id);
    }











}
