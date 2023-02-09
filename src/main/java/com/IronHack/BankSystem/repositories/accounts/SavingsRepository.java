package com.IronHack.BankSystem.repositories.accounts;

import com.IronHack.BankSystem.models.accounts.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsRepository extends JpaRepository<Savings,Integer> {
}
