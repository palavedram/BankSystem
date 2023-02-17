package com.IronHack.BankSystem.Services.users;

import com.IronHack.BankSystem.Services.users.imp.AdminServiceImplement;
import com.IronHack.BankSystem.models.DTOs.AccountHolderDTO;
import com.IronHack.BankSystem.models.DTOs.CheckingDTO;
import com.IronHack.BankSystem.models.DTOs.CreditDTO;
import com.IronHack.BankSystem.models.DTOs.SavingsDTO;
import com.IronHack.BankSystem.models.Enum.AcountType;
import com.IronHack.BankSystem.models.Enum.Status;
import com.IronHack.BankSystem.models.Enum.TransactionType;
import com.IronHack.BankSystem.models.accounts.*;
import com.IronHack.BankSystem.models.security.Role;
import com.IronHack.BankSystem.models.security.User;
import com.IronHack.BankSystem.models.users.AccountHolder;
import com.IronHack.BankSystem.models.users.Address;
import com.IronHack.BankSystem.models.users.Admin;
import com.IronHack.BankSystem.repositories.accounts.*;
import com.IronHack.BankSystem.repositories.security.RoleRepository;
import com.IronHack.BankSystem.repositories.security.UserRepository;
import com.IronHack.BankSystem.repositories.users.AccountHolderRepository;
import com.IronHack.BankSystem.repositories.users.AddressRepository;
import com.IronHack.BankSystem.repositories.users.AdminRepository;
import com.IronHack.BankSystem.repositories.users.ThirdPartyRespository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Transactional
@Service
public class AdminService implements AdminServiceImplement {
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ThirdPartyRespository thirdPartyRespository;
    @Autowired
    private TransactionRepository transactionRepository;

    /**
     *
     * CREAR
     */

    public Savings createSavings(SavingsDTO savingsDTO) {

        //find the accountholder
        AccountHolder accountHolderDB = accountHolderRepository.findById(savingsDTO.getAccountHolderId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "AccountHolder not found"));

        Savings savings = new Savings();
        savings.setPrimaryOwner(accountHolderDB.getName());
        savings.setAccountType(AcountType.SAVINGS);
        savings.setStatus(Status.ACTIVE);
        savings.setSecretKey(savingsDTO.getSecretKey());
        savings.setAccountHolder(accountHolderDB);

            if(savingsDTO.getInterestRate() != null){
                if(savingsDTO.getInterestRate()>0.0025 && savingsDTO.getInterestRate()<=0.5){
                    savings.setInterestRate(savingsDTO.getInterestRate());
                }else{
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"InterestRate out of Bounds, Please check");
                }
            }
            if(savingsDTO.getMinimumBalance() != null){
                if(savingsDTO.getMinimumBalance()>100 && savingsDTO.getMinimumBalance()<=1000){
                    savings.setMinimumBalance(BigDecimal.valueOf(savingsDTO.getMinimumBalance()));
                }else{
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Minimum Balance Out of Bounds, (100-1000)");
                }
        }
           return savingsRepository.save(savings);
    }

    public CheckingAccount createChecking(CheckingDTO checkingDTO) {

        AccountHolder accountHolderDB = accountHolderRepository.findById(checkingDTO.getAccountHolderId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "AccountHolder not found"));

        if (accountHolderDB.getEdad() >= 25) {
            // La persona es mayor de edad
            CheckingAccount newChecking;
            newChecking = new CheckingAccount();
            newChecking.setPrimaryOwner(accountHolderDB.getName());
            newChecking.setAccountType(AcountType.CHECKING);
            newChecking.setStatus(Status.ACTIVE);
            newChecking.setSecretKey(checkingDTO.getSecretKey());
            newChecking.setAccountHolder(accountHolderDB);
            return checkingRepository.save(newChecking);


        } else {
            StudentChecking newStudentAccount;
            // La persona es menor de edad
            newStudentAccount = new StudentChecking();
            newStudentAccount.setPrimaryOwner(accountHolderDB.getName());
            newStudentAccount.setAccountType(AcountType.STUDENT);
            newStudentAccount.setStatus(Status.ACTIVE);
            newStudentAccount.setSecretKey(checkingDTO.getSecretKey());
            newStudentAccount.setAccountHolder(accountHolderDB);
            return studentCheckingRepository.save(newStudentAccount);
        }


    }

    public CreditCardAccount createCredit(CreditDTO creditDTO) {
        //find the accountholder
        AccountHolder accountHolderDB = accountHolderRepository.findById(creditDTO.getAccountHolderId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "AccountHolder not found"));

        CreditCardAccount creditCardAccount = new CreditCardAccount();
        creditCardAccount.setPrimaryOwner(accountHolderDB.getName());
        creditCardAccount.setAccountType(AcountType.CREDIT);
        creditCardAccount.setStatus(Status.ACTIVE);
        creditCardAccount.setSecretKey(creditDTO.getSecretKey());
        creditCardAccount.setAccountHolder(accountHolderDB);

        if(creditDTO.getCreditLimit() != null){
            if(creditDTO.getCreditLimit()>100 && creditDTO.getCreditLimit()<100000){
            creditCardAccount.setCreditLimit(creditDTO.getCreditLimit());
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Credit Limit Out of Bounds, Please check");
            }
        }else{
            creditCardAccount.setCreditLimit(100);
        }
        if(creditDTO.getInterestRate() != null){
            if(creditDTO.getInterestRate()>=0.1 && creditDTO.getInterestRate()<=0.2){
                creditCardAccount.setInterestRate(creditDTO.getInterestRate());
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"InterestRate Out of Bounds, Please check");
            }
        }else{
            creditCardAccount.setInterestRate(0.2);
        }

        return creditCardRepository.save(creditCardAccount);


    }

    public AccountHolder createAccountHolder(AccountHolderDTO accountHolderDTO) {
        //comporbar que username no existe en la base de datos.
        String username = accountHolderDTO.getUsername();
        if (accountHolderRepository.findByUsername(username) != null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN ,"El nombre de usuario  "+username+"  ya está en uso");
        }
        //create user
        AccountHolder newAccountHolder = new AccountHolder();
        //calculate age
        LocalDate now = LocalDate.now();
        LocalDate birthDate = accountHolderDTO.getDateOfBirth();
        Period edad = Period.between(birthDate, now);
        //set user attributes
        newAccountHolder.setName(accountHolderDTO.getName());
        newAccountHolder.setUsername(accountHolderDTO.getUsername());
        //encriptar contraseña
        //newAccountHolder.setPassword(accountHolderDTO.getPassword());
        String encodedPassword = passwordEncoder.encode(accountHolderDTO.getPassword());
        newAccountHolder.setPassword(encodedPassword);
        newAccountHolder.setEmail(accountHolderDTO.getEmail());
        newAccountHolder.setDateOfBirth(accountHolderDTO.getDateOfBirth());//Localdate
        newAccountHolder.setEdad(edad.getYears());
        accountHolderRepository.save(newAccountHolder);
        // add role to user
        Role role = roleRepository.findByName("ROLE_ACCOUNT_HOLDER"); // obtenemos el rol "ROLE_ACCOUNT_HOLDER"
        newAccountHolder.getRoles().add(role); // agregamos el rol al usuario
        accountHolderRepository.save(newAccountHolder); // guardamos la relación entre usuario y rol

        //create address
        Address newAddress = new Address();
        newAddress.setStreet(accountHolderDTO.getStreet());//String
        newAddress.setCity(accountHolderDTO.getCity());//String
        newAddress.setCountry(accountHolderDTO.getCountry());//String
        newAddress.setZipCode(accountHolderDTO.getZipCode());//String
        newAddress.setAccountHolder(newAccountHolder);
        addressRepository.save(newAddress);
        newAccountHolder.setPrimaryAddress(newAddress);
        newAccountHolder.setMailingAddress(newAddress);
        return newAccountHolder;
    }

    public Admin createAdmin(String adminName) {
         Admin newAdmin = new Admin();
         newAdmin.setName(adminName);
         newAdmin.setUsername("Admin "+adminName);
         String encodedPassword = passwordEncoder.encode("admin1234");
         newAdmin.setPassword(encodedPassword);
        // add role to user
        Role role = roleRepository.findByName("ROLE_ADMIN"); // obtenemos el rol "ROLE_ACCOUNT_HOLDER"
        newAdmin.getRoles().add(role); // agregamos el rol al usuario
        return adminRepository.save(newAdmin);
    }





    /**
     *
     * ACTUALIZAR
     */

    public Account UpdateAccountBalance(Integer accountId, Double newBalance) {
        Account accounDB = accountRepository.findById(accountId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        accounDB.setBalance(BigDecimal.valueOf(newBalance));
        accountRepository.save(accounDB);
        return accounDB;
                //api/admin/updateBalance
    }

    public Account operations(Integer accountId, String type, Integer amount) {

        Account accounDB = accountRepository.findById(accountId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));



        switch (type.toLowerCase()){
            case "withdraw":
                if (accounDB.getBalance().compareTo(BigDecimal.valueOf(amount)) < 0){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
                }
                accounDB.setBalance(accounDB.getBalance().subtract(BigDecimal.valueOf(amount)));
                Transaction withdraw = new Transaction();
                withdraw.setTransactionType(TransactionType.WITHDRAW_MONEY);
                withdraw.setAmount(BigDecimal.valueOf(amount));
                withdraw.setSender(accounDB);
                withdraw.setSenderBalance(accounDB.getBalance());
                withdraw.setDateTime(LocalDateTime.now());
                transactionRepository.save(withdraw);
                return accounDB;
            case "deposit":
                accounDB.setBalance(accounDB.getBalance().add(BigDecimal.valueOf(amount)));
                Transaction deposit = new Transaction();
                deposit.setTransactionType(TransactionType.DEPOSIT_MONEY);
                deposit.setAmount(BigDecimal.valueOf(amount));
                deposit.setDateTime(LocalDateTime.now());
                deposit.setSender(accounDB);
                deposit.setSenderBalance(accounDB.getBalance());
                transactionRepository.save(deposit);
                return accounDB;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "please check the input ");
        }

    }

    public Account maintenanceFee(Integer id) {

        Account accountDB = accountRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found"));

        if (accountDB instanceof CheckingAccount) {
            CheckingAccount checkingAccount = (CheckingAccount) accountDB;
            checkingAccount.applyMantenanceFee();
            accountRepository.save(checkingAccount);
            Transaction maintenance = new Transaction();
            maintenance.setTransactionType(TransactionType.MAINTENANCE_FEE);
            maintenance.setAmount(checkingAccount.getMonthlyMaintenanceFee());
            maintenance.setSender(accountDB);
            maintenance.setSenderBalance(accountDB.getBalance());
            maintenance.setDateTime(LocalDateTime.now());
            transactionRepository.save(maintenance);

        }

        return accountDB;

    }

    public Account penaltyFee(Integer id) {
        Account accountDB = accountRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found"));

        if (accountDB instanceof CheckingAccount) {
            CheckingAccount checkingAccount = (CheckingAccount) accountDB;
            if (checkingAccount.getBalance().compareTo(checkingAccount.getMinimumBalance()) < 0) {
                checkingAccount.applyPenaltyFee();
                accountRepository.save(checkingAccount);
                Transaction maintenance = new Transaction();
                maintenance.setTransactionType(TransactionType.PENALTY_FEE);
                maintenance.setAmount(checkingAccount.getPENALTY_FEE());
                maintenance.setSender(accountDB);
                maintenance.setSenderBalance(accountDB.getBalance());
                maintenance.setDateTime(LocalDateTime.now());
                transactionRepository.save(maintenance);
            }
        }
        if (accountDB instanceof Savings) {
            Savings savings = (Savings) accountDB;
            if (savings.getBalance().compareTo(savings.getMinimumBalance()) < 0) {
                savings.applyPenaltyFee();
                accountRepository.save(savings);
                Transaction maintenance = new Transaction();
                maintenance.setTransactionType(TransactionType.PENALTY_FEE);
                maintenance.setAmount(savings.getPENALTY_FEE());
                maintenance.setSender(accountDB);
                maintenance.setSenderBalance(accountDB.getBalance());
                maintenance.setDateTime(LocalDateTime.now());
                transactionRepository.save(maintenance);
            }
        }

        return accountDB;

    }

    public AccountHolder updateAccountHolder(Integer id, AccountHolder accountHolder) {
        return null;
    }

    public CheckingAccount updateChecking(Integer id, CheckingAccount checkingAccount) {
        return null;
    }

    public Savings updateSaving(Integer id, Savings savings) {
        return null;
    }

    public CreditCardAccount updateCredit(Integer id, CreditCardAccount creditCardAccount) {
        return null;
    }

    /**
     *
     * BORRAR
     */
    public AccountHolder deleteAccountHolder(Integer id) {
        AccountHolder accountHolder = accountHolderRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"AccountHolder con "+ id+" no existe en la base de datos"));
        accountHolderRepository.delete(accountHolder);
        return accountHolder;
    }

    public Account deleteAccount(Integer id) {
        Account account = accountRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"La cuenta con "+ id+" no existe en la base de datos"));
        accountRepository.delete(account);

        return account;
    }


    /**
     * BUSCAR
     * api/admin/
     */
    public AccountHolder findAccountHolderById(Integer id) {
        return accountHolderRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"AccountHolder not found"));
    }
    public List<AccountHolder> findAllAccountHolder() {
        return accountHolderRepository.findAll();
    }

    public Account findAccountById(Integer id) {
        Account accountDB = accountRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found"));
        return accountDB;


    }


    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }
    public List<Savings> findAllSavings() {
        return savingsRepository.findAll();
    }
    public List<CheckingAccount> findAllCheckings() {
        return checkingRepository.findAll();
    }
    public List<CreditCardAccount> findAllCredit() {
        return creditCardRepository.findAll();
    }
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    public List<?> findAnyThing(String whatToGet) {

        if(whatToGet != null && !whatToGet.isEmpty()) {
            switch (whatToGet.toLowerCase()) {
                case "accounts":
                    return accountRepository.findAll();
                case "users":
                    return userRepository.findAll();
                case "checking":
                    return checkingRepository.findAll();
                case "saving":
                    return savingsRepository.findAll();
                case "credit":
                    return creditCardRepository.findAll();
                case "accountholder":
                    return accountHolderRepository.findAll();
                case "admin":
                    return adminRepository.findAll();
                case "thirdparty":
                    return thirdPartyRespository.findAll();
                case "transactions":
                    return transactionRepository.findAll();
                default:
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "please check the input ");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "please introduce valid input");
        }
    }




}
