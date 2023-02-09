package com.IronHack.BankSystem;

import com.IronHack.BankSystem.models.Enum.Status;
import com.IronHack.BankSystem.models.accounts.Savings;
import com.IronHack.BankSystem.models.users.AccountHolder;
import com.IronHack.BankSystem.models.Address;
import com.IronHack.BankSystem.repositories.accounts.SavingsRepository;
import com.IronHack.BankSystem.repositories.users.AccountHolderRepository;
import com.IronHack.BankSystem.repositories.users.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class BankSystemApplication implements CommandLineRunner {
	@Autowired
	SavingsRepository savingsRepository;
	@Autowired
	AccountHolderRepository accountHolderRepository;
	@Autowired
	AddressRepository addressRepository;

	public static void main(String[] args) {
		SpringApplication.run(BankSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {



	}
}
