package com.IronHack.BankSystem.Services.users.imp;


import com.IronHack.BankSystem.models.accounts.Account;
import com.IronHack.BankSystem.models.accounts.Transaction;
import org.springframework.security.core.Authentication;

import java.math.BigDecimal;
import java.util.List;

public interface AccountHolderServiceImplement {
    List<Account> find(Authentication autentication);

    Account depositWithdraw(Authentication authentication, Integer id, String type, Integer amount);

    Transaction createTransactionbyOwner(Authentication authentication,Integer senderAccountId, Integer receiverId, BigDecimal amount);

    Transaction createTransactionToSameOwnerAccounts(Authentication authentication, Integer senderAccountId, Integer receiverAccountId, BigDecimal amount);


    //List<Account> findAccountHolderAccounts(Authentication authentication);

    // Account depositWithdraw(Authentication authentication, Integer id,String type, Integer amount);
}
