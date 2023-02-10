package com.IronHack.BankSystem.repositories.accounts;

import com.IronHack.BankSystem.models.accounts.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingRepository extends JpaRepository<CheckingAccount,Integer> {
}
