package com.IronHack.BankSystem.repositories.users;

import com.IronHack.BankSystem.models.users.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
}
