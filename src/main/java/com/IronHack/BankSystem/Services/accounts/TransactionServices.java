package com.IronHack.BankSystem.Services.accounts;

import com.IronHack.BankSystem.Services.accounts.impl.TransactionServiceImplement;
import com.IronHack.BankSystem.models.Enum.Status;
import com.IronHack.BankSystem.models.accounts.Account;
import com.IronHack.BankSystem.models.accounts.Transaction;
import com.IronHack.BankSystem.repositories.accounts.AccountRepository;
import com.IronHack.BankSystem.repositories.accounts.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TransactionServices implements TransactionServiceImplement {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;



    public Transaction create(Integer fromAccountId, Integer toAccountId, BigDecimal amount) {

        LocalDateTime now = LocalDateTime.now();
        Account fromAccount = accountRepository.findById(fromAccountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        Account toAccount = accountRepository.findById(toAccountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        if (fromAccount.getBalance().compareTo(amount) < 0){ //fromAccount es menor que amount, lo cual significa que no hay suficientes fondos para realizar la transacción.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
        }

        /**
         * Despues de comprobar que hay fondos suficientes se hace la transaccion.
         * Despues de comporvar que no hay fraude
         */
        Transaction transaction = new Transaction();
        transaction.setFromAccount(fromAccount);
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        transaction.setToAccount(toAccount);
        toAccount.setBalance(toAccount.getBalance().add(amount));
        transaction.setAmount(amount);

        /**
         * establezer el tiempo de la transaccion
         * Date DateTimeFormatter es para poder visualizar como string
         */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        transaction.setFormattedDateTime(now.format(formatter));//String
        transaction.setDateTime(now);//LocalDateTime
        transactionRepository.save(transaction);

        /**
         * Guardar en el repositorio el nuevo amount de la cuenta que envia.
         * guardar ese monto en una variable de transacion para que sea mas visual.
         */
        transaction.setFromAccountBalance(fromAccount.getBalance());
        accountRepository.save(fromAccount);
        /**
         *Lo mismo para la cuenta receptora
         */
        transaction.setToAccountBalance(toAccount.getBalance());
        accountRepository.save(toAccount);

        return transaction;





    }
}
