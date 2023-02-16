package com.IronHack.BankSystem;

import com.IronHack.BankSystem.Services.security.impl.UserService;
import com.IronHack.BankSystem.models.security.Role;
import com.IronHack.BankSystem.models.security.User;
import com.IronHack.BankSystem.repositories.accounts.SavingsRepository;
import com.IronHack.BankSystem.repositories.users.AccountHolderRepository;
import com.IronHack.BankSystem.repositories.users.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

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

		@Bean
		PasswordEncoder passwordEncoder() {
			return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		}

		@Bean
		CommandLineRunner run(UserService userService) {
			return args -> {
				userService.saveRole(new Role(null, "ROLE_ACCOUNT_HOLDER"));
				userService.saveRole(new Role(null, "ROLE_ADMIN"));
				userService.saveRole(new Role(null, "ROLE_THIRD_PARTY"));

			/*	userService.saveUser(new User(null, "John Doe", "john", "1234", new ArrayList<>()));
				userService.saveUser(new User(null, "James Smith", "james", "1234", new ArrayList<>()));
				userService.saveUser(new User(null, "Jane Carry", "jane", "1234", new ArrayList<>()));
				userService.saveUser(new User(null, "Chris Anderson", "chris", "1234", new ArrayList<>()));*/

			/*	userService.addRoleToUser("john", "ROLE_USER");
				userService.addRoleToUser("james", "ROLE_ADMIN");
				userService.addRoleToUser("jane", "ROLE_USER");
				userService.addRoleToUser("chris", "ROLE_ADMIN");
				userService.addRoleToUser("chris", "ROLE_USER");*/
			};
		}
	@Override
	public void run(String... args) throws Exception {
	}
}
