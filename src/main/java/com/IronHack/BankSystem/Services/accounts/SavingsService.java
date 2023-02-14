package com.IronHack.BankSystem.Services.accounts;


import com.IronHack.BankSystem.Services.accounts.impl.SavingsServiceImplement;
import com.IronHack.BankSystem.models.accounts.Savings;
import com.IronHack.BankSystem.repositories.accounts.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service

public class SavingsService implements SavingsServiceImplement {
@Autowired
    SavingsRepository savingsRepository;


    public List<Savings> findAll() {
        return savingsRepository.findAll();
    }

    public Savings findById(Integer id) {
        return savingsRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Savings Account not found"));
    }

    public Savings create(Savings savings) {
        return savingsRepository.save(savings);
    }

    @Override
    public Savings update(Savings savings) {
        return null;
    }


    public Savings delete(Integer id) {

        Savings deleteSavings = savingsRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"La cuenta Savings con "+ id+" no existe en la base de datos"));
        savingsRepository.delete(deleteSavings);


        return deleteSavings;
    }



}
