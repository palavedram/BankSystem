package com.IronHack.BankSystem.Services.accounts;

import com.IronHack.BankSystem.Services.accounts.impl.TransactionServiceImplement;
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



    public Transaction create(Integer senderId, Integer receiverId, BigDecimal amount) {

        LocalDateTime now = LocalDateTime.now();
        Account sender = accountRepository.findById(senderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        Account receiver = accountRepository.findById(receiverId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        if (sender.getBalance().compareTo(amount) < 0){ //fromAccount es menor que amount, lo cual significa que no hay suficientes fondos para realizar la transacción.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
        }

        /**
         * Despues de comprobar que hay fondos suficientes se hace la transaccion.
         * Despues de comporvar que no hay fraude
         */
        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        sender.setBalance(sender.getBalance().subtract(amount));
        transaction.setReceiver(receiver);
        receiver.setBalance(receiver.getBalance().add(amount));
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
        transaction.setSenderBalance(sender.getBalance());
        transactionRepository.save(transaction);
        accountRepository.save(sender);
        /**
         *Lo mismo para la cuenta receptora
         */
        transaction.setReceiverBalance(receiver.getBalance());
        transactionRepository.save(transaction);
        accountRepository.save(receiver);

        // Aplicar penalización si el balance es menor que el mínimo

    /*    if (fromAccount instanceof CheckingAccount) {
            CheckingAccount checkingAccount = (CheckingAccount) fromAccount;
            if (checkingAccount.getBalance().compareTo(checkingAccount.getMinimumBalance()) < 0) {
                checkingAccount.applyPenaltyFee();
                accountRepository.save(checkingAccount);
            }
        }*/

        return transaction;





    }

    public List<Transaction> findAllTransaction() {
        return transactionRepository.findAll();

    }
}
