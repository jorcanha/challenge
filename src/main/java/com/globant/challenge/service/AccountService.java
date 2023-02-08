package com.globant.challenge.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.challenge.dto.AccountDTO;
import com.globant.challenge.dto.TransactionDTO;
import com.globant.challenge.model.Account;
import com.globant.challenge.model.Customer;
import com.globant.challenge.repository.AccountRepository;
import com.globant.challenge.repository.CustomerRepository;

@Service
public class AccountService {

	private static final Logger LOG = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public List<AccountDTO> findAll() {

		Iterable<Account> accounts = accountRepository.findAll();

		List<AccountDTO> accountsDTO = new ArrayList<>();;

		accounts.forEach(account -> {
			AccountDTO accountDTO = new AccountDTO();
			accountDTO.setAccountId(account.getAccountId());
			accountDTO.setCustomerId(account.getCustomer().getCustomerId());
			accountDTO.setAccountNumber(account.getAccountNumber());
			accountDTO.setAccountType(account.getAccountType());
			accountDTO.setInitialBalance(account.getInitialBalance());
			accountDTO.setStatus(account.getStatus());
			
			accountsDTO.add(accountDTO);
		});
		
		return accountsDTO;
	}

	public String createAccount(AccountDTO accountDTO) {
		
		Optional<Customer> customer = customerRepository.findById(accountDTO.getCustomerId());
		
		Account newAccount = new Account();
		
		newAccount.setCustomer(customer.get());
		newAccount.setAccountNumber(accountDTO.getAccountNumber());
		newAccount.setAccountType(accountDTO.getAccountType());
		newAccount.setInitialBalance(accountDTO.getInitialBalance());
		newAccount.setStatus(accountDTO.getStatus());
		
		newAccount = accountRepository.save(newAccount);

		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setTransaction_type("Deposito");
		transactionDTO.setDate(new Date());
		transactionDTO.setAmount(accountDTO.getInitialBalance());
		transactionDTO.setBalance(accountDTO.getInitialBalance());
		
		String result = transactionService.createTransaction(transactionDTO, newAccount);
		LOG.debug("Creatin initial transaction for new account {}", result);
		
		return String.format("Created new Account with id %s for Customer %s", newAccount.getAccountId(), customer.get().getCustomerId());
	}
}
