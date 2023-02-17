package com.IronHack.BankSystem.repositories.accounts;

import com.IronHack.BankSystem.models.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

    public List<Account> findByAccountHolderUsername(String Username);


}
