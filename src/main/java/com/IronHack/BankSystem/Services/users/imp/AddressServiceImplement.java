package com.IronHack.BankSystem.Services.users.imp;

import com.IronHack.BankSystem.models.Address;

import java.util.List;

public interface AddressServiceImplement {
    Address create(Address address);

    List<Address> findAll();

    Address update(Address address);

    Address delete(Integer id);

    Address findById(Integer id);
}
