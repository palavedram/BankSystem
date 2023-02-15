package com.IronHack.BankSystem;

import com.IronHack.BankSystem.models.DTOs.AccountDTO;
import com.IronHack.BankSystem.models.DTOs.AccountHolderDTO;
import com.IronHack.BankSystem.models.Enum.AcountType;
import com.IronHack.BankSystem.models.accounts.Account;
import com.IronHack.BankSystem.models.accounts.CheckingAccount;
import com.IronHack.BankSystem.models.accounts.Transaction;
import com.IronHack.BankSystem.models.users.AccountHolder;
import com.IronHack.BankSystem.models.users.Address;
import com.IronHack.BankSystem.repositories.accounts.AccountRepository;
import com.IronHack.BankSystem.repositories.accounts.TransactionRepository;
import com.IronHack.BankSystem.repositories.users.AccountHolderRepository;
import com.IronHack.BankSystem.repositories.users.AddressRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BankSystemApplicationTests {

	@Autowired
	WebApplicationContext context;

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	AccountHolderRepository accountHolderRepository;
	@Autowired
	AddressRepository addressRepository;


	private MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();


	AccountHolderDTO accountHolderDTO =new AccountHolderDTO("TestName1","LastNameTest1","testemail@test.com",LocalDate.of(1993,05,20),"testStreet1","TestCity1","country1","08111");
	AccountHolderDTO accountHolderDTO2 =new AccountHolderDTO("TestName2","LastNameTest2","testemail@test.com",LocalDate.of(2015,05,20),"testStreet2","TestCity2","country2","08222");

	AccountDTO accountDTO = new AccountDTO(1,500,"1234",null,null,null,null,"checking");
	AccountDTO accountDTO2 = new AccountDTO(2,500,"1234",null,null,null,null,"checking");

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

		Address address = addressRepository.save(new Address("testStreet1","TestCity1","country1","08111"));
		Address address1 = addressRepository.save(new Address("testStreet2","TestCity2","country2","08222"));


	}


	@Test
	void contextLoads() {
	}

	@Test
	public void createAccountHolder_Test()throws Exception{
		AccountHolderDTO accountHolderDTO =new AccountHolderDTO("TestName1","LastNameTest1","testemail@test.com",LocalDate.of(1993,05,20),"testStreet1","TestCity1","country1","08111");
		AccountHolderDTO accountHolderDTO2 =new AccountHolderDTO("TestName2","LastNameTest2","testemail@test.com",LocalDate.of(2015,05,20),"testStreet2","TestCity2","country2","08222");

		String body = objectMapper.writeValueAsString(accountHolderDTO);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/accountHolder")
				.content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted()).andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
		assertTrue(mvcResult.getResponse().getContentAsString().contains("TestName1"));
	}

}
