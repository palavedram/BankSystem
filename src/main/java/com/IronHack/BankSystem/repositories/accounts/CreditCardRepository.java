package com.IronHack.BankSystem.repositories.accounts;

import com.IronHack.BankSystem.models.accounts.CreditCardAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardAccount,Integer> {
}
