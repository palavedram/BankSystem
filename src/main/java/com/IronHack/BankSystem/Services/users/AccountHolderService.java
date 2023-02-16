package com.IronHack.BankSystem.Services.users;

import com.IronHack.BankSystem.Services.users.imp.AccountHolderServiceImplement;
import com.IronHack.BankSystem.models.security.Role;
import com.IronHack.BankSystem.models.users.Address;
import com.IronHack.BankSystem.models.DTOs.AccountHolderDTO;

import com.IronHack.BankSystem.models.users.AccountHolder;
import com.IronHack.BankSystem.repositories.security.RoleRepository;
import com.IronHack.BankSystem.repositories.users.AccountHolderRepository;
import com.IronHack.BankSystem.repositories.users.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Transactional
@Service
public class AccountHolderService implements AccountHolderServiceImplement {

    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public List<AccountHolder> findAll() {
        return accountHolderRepository.findAll();
    }

    public AccountHolder create(AccountHolderDTO accountHolderDTO) {
        //comporbar que username no existe en la base de datos.
        String username = accountHolderDTO.getUsername();
        if (accountHolderRepository.findByUsername(username) != null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN ,"El nombre de usuario  "+username+"  ya está en uso");
        }
        //create user
        AccountHolder newAccountHolder = new AccountHolder();
        //calculate age
            LocalDate now = LocalDate.now();
            LocalDate birthDate = accountHolderDTO.getDateOfBirth();
            Period edad = Period.between(birthDate, now);
        //set user attributes
            newAccountHolder.setName(accountHolderDTO.getName());
            newAccountHolder.setUsername(accountHolderDTO.getUsername());
        //encriptar contraseña
            //newAccountHolder.setPassword(accountHolderDTO.getPassword());
        String encodedPassword = passwordEncoder.encode(accountHolderDTO.getPassword());
        newAccountHolder.setPassword(encodedPassword);
            newAccountHolder.setEmail(accountHolderDTO.getEmail());
            newAccountHolder.setDateOfBirth(accountHolderDTO.getDateOfBirth());//Localdate
            newAccountHolder.setEdad(edad.getYears());
            accountHolderRepository.save(newAccountHolder);
        // add role to user
        Role role = roleRepository.findByName("ROLE_ACCOUNT_HOLDER"); // obtenemos el rol "ROLE_ACCOUNT_HOLDER"
        newAccountHolder.getRoles().add(role); // agregamos el rol al usuario
        accountHolderRepository.save(newAccountHolder); // guardamos la relación entre usuario y rol

        //create address
        Address newAddress = new Address();
            newAddress.setStreet(accountHolderDTO.getStreet());//String
            newAddress.setCity(accountHolderDTO.getCity());//String
            newAddress.setCountry(accountHolderDTO.getCountry());//String
            newAddress.setZipCode(accountHolderDTO.getZipCode());//String
            newAddress.setAccountHolder(newAccountHolder);
            addressRepository.save(newAddress);
            newAccountHolder.setPrimaryAddress(newAddress);
            newAccountHolder.setMailingAddress(newAddress);
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
                new ResponseStatusException(HttpStatus.NOT_FOUND,"AccountHolder not found"));
    }
}
