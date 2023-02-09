package com.IronHack.BankSystem.Controllers;

import com.IronHack.BankSystem.Services.users.imp.AddressServiceImplement;
import com.IronHack.BankSystem.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {
    @Autowired
    AddressServiceImplement addressServiceImplement;

    //CRUD sobre la entidad Address

    //Buscar todos los Address
    @GetMapping("/addresses")
    public List<Address> findAll(){
        return addressServiceImplement.findAll();
    }

    //Buscar Address por ID.
    @GetMapping("/addresses/{id}")
    public Address findById(@PathVariable Integer id){
        return addressServiceImplement.findById(id);
    }


    //Crear un Address
    @PostMapping("/addresses")
    public Address create (@RequestBody Address adress){
        return addressServiceImplement.create(adress);

    }

    //Actualizar un Address
    @PutMapping("/addresses")
    public Address update(@RequestParam Address address){
        return addressServiceImplement.update(address);
    }
    //Borrar un Address
    @DeleteMapping("/addresses/{id}")
    public Address delete(@PathVariable Integer id){
        return addressServiceImplement.delete(id);
    }



}

