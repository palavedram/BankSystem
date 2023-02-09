package com.IronHack.BankSystem.repositories.users;

import com.IronHack.BankSystem.models.users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder,Integer> {
}
