package com.IronHack.BankSystem.Services.users;

import com.IronHack.BankSystem.Services.users.imp.AccountHolderServiceImplement;
import com.IronHack.BankSystem.models.Enum.TransactionType;
import com.IronHack.BankSystem.models.accounts.Account;
import com.IronHack.BankSystem.models.accounts.Transaction;

import com.IronHack.BankSystem.models.users.AccountHolder;
import com.IronHack.BankSystem.repositories.accounts.AccountRepository;
import com.IronHack.BankSystem.repositories.accounts.TransactionRepository;
import com.IronHack.BankSystem.repositories.security.RoleRepository;
import com.IronHack.BankSystem.repositories.security.UserRepository;
import com.IronHack.BankSystem.repositories.users.AccountHolderRepository;
import com.IronHack.BankSystem.repositories.users.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Transactional
@Service
public class AccountHolderService implements AccountHolderServiceImplement {

    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;


    public List<Account> find(Authentication autentication) {
        return accountRepository.findByAccountHolderUsername(autentication.getPrincipal().toString());
    }

    public Account depositWithdraw(Authentication authentication, Integer id, String type, Integer amount) {

        Account accountDB = accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        List<Account> accountList = accountRepository.findByAccountHolderUsername(authentication.getPrincipal().toString());

        if (accountList.contains(accountDB)) {
            // La cuenta está asociada al usuario que ha iniciado sesión
            switch (type.toLowerCase()) {
                case "withdraw":
                    if (accountDB.getBalance().compareTo(BigDecimal.valueOf(amount)) < 0) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
                    }
                    accountDB.setBalance(accountDB.getBalance().subtract(BigDecimal.valueOf(amount)));
                    Transaction withdraw = new Transaction();
                    withdraw.setTransactionType(TransactionType.WITHDRAW_MONEY);
                    withdraw.setAmount(BigDecimal.valueOf(amount));
                    withdraw.setSender(accountDB);
                    withdraw.setSenderBalance(accountDB.getBalance());
                    withdraw.setDateTime(LocalDateTime.now());
                    transactionRepository.save(withdraw);
                    return accountDB;
                case "deposit":
                    accountDB.setBalance(accountDB.getBalance().add(BigDecimal.valueOf(amount)));
                    Transaction deposit = new Transaction();
                    deposit.setTransactionType(TransactionType.DEPOSIT_MONEY);
                    deposit.setAmount(BigDecimal.valueOf(amount));
                    deposit.setDateTime(LocalDateTime.now());
                    deposit.setSender(accountDB);
                    deposit.setSenderBalance(accountDB.getBalance());
                    transactionRepository.save(deposit);
                    return accountDB;
                default:
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "please check the input ");
            }

        } else {
            // La cuenta no está asociada al usuario que ha iniciado sesión
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized access to account");
        }
    }

    public Transaction createTransactionbyOwner(Authentication authentication,Integer senderAccountId, Integer receiverAccountId, BigDecimal amount) {
        LocalDateTime now = LocalDateTime.now();

        AccountHolder accountHolder = accountHolderRepository.findByUsername(authentication.getPrincipal().toString());

        // Buscamos la cuenta emisora
        Account senderAccount = accountRepository.findById(senderAccountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        //Buscamos la cuenta receptora
        Account receiverAccount = accountRepository.findById(receiverAccountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        // Verificamos que la cuenta emisora pertenece al account holder logueado
        if (!senderAccount.getAccountHolder().getId().equals(accountHolder.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sender account does not belong to the logged account holder");
        }

        //Verificamos que la cuenta tenga fondos suficientes
        if (senderAccount.getBalance().compareTo(amount) < 0){ //fromAccount es menor que amount, lo cual significa que no hay suficientes fondos para realizar la transacción.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
        }
        // Resto el balance de la cuenta emisora y lo sumo al balance de la cuenta receptora
        BigDecimal senderBalance = senderAccount.getBalance();
        BigDecimal receiverBalance = receiverAccount.getBalance();
        senderAccount.setBalance(senderBalance.subtract(amount));
        receiverAccount.setBalance(receiverBalance.add(amount));

        // Creamos la transacción
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.WIRE_TRANSFER);
        transaction.setSender(senderAccount);
        transaction.setReceiver(receiverAccount);
        transaction.setAmount(amount);
        transaction.setDateTime(now);
        transaction.setSenderBalance(senderAccount.getBalance());
        transaction.setReceiverBalance(receiverAccount.getBalance());

        // Guardamos la transacción y las cuentas actualizadas
        transactionRepository.save(transaction);
        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);

        return transaction;
    }

    public Transaction createTransactionToSameOwnerAccounts(Authentication authentication, Integer senderAccountId, Integer receiverAccountId, BigDecimal amount) {
        LocalDateTime now = LocalDateTime.now();

        AccountHolder accountHolder = accountHolderRepository.findByUsername(authentication.getPrincipal().toString());

        // Obtenemos todas las cuentas del account holder logueado
        List<Account> accountList = accountRepository.findByAccountHolderUsername(authentication.getPrincipal().toString());
        // Buscamos la cuenta emisora
        Account senderAccount = accountRepository.findById(senderAccountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        // Buscamos la cuenta receptora
        Account receiverAccount = accountRepository.findById(receiverAccountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        // Verificamos que la cuenta emisora pertenece al account holder logueado
        if (!senderAccount.getAccountHolder().getId().equals(accountHolder.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sender account does not belong to the logged account holder");
        }
        // Verificamos que la cuenta receptora pertenece al account holder logueado
        if (!receiverAccount.getAccountHolder().getId().equals(accountHolder.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Receiver account does not belong to the logged account holder");
        }

        //Verificamos que la cuenta tenga fondos suficientes
        if (senderAccount.getBalance().compareTo(amount) < 0){ //fromAccount es menor que amount, lo cual significa que no hay suficientes fondos para realizar la transacción.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
        }

        // Resto el balance de la cuenta emisora y lo sumo al balance de la cuenta receptora
        BigDecimal senderBalance = senderAccount.getBalance();
        BigDecimal receiverBalance = receiverAccount.getBalance();
        senderAccount.setBalance(senderBalance.subtract(amount));
        receiverAccount.setBalance(receiverBalance.add(amount));

        // Creamos la transacción
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.WIRE_TRANSFER);
        transaction.setSender(senderAccount);
        transaction.setReceiver(receiverAccount);
        transaction.setAmount(amount);
        transaction.setDateTime(now);
        transaction.setSenderBalance(senderAccount.getBalance());
        transaction.setReceiverBalance(receiverAccount.getBalance());

        // Guardamos la transacción y las cuentas actualizadas
        transactionRepository.save(transaction);
        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);

        return transaction;
    }
}
