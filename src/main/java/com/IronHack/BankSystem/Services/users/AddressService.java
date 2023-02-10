package com.IronHack.BankSystem.Services.users;

import com.IronHack.BankSystem.Services.users.imp.AddressServiceImplement;
import com.IronHack.BankSystem.models.users.Address;
import com.IronHack.BankSystem.repositories.users.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AddressService implements AddressServiceImplement {
    @Autowired
    AddressRepository addressRepository;

    public Address create(Address address) {
        return addressRepository.save(address);
    }


    public List<Address> findAll() {
        return addressRepository.findAll();
    }


    public Address update(Address address) {
        return null;
    }


    public Address delete(Integer id) {
        return null;
    }


    public Address findById(Integer id) {
        return addressRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Address not found"));

    }
}
