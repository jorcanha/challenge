package com.globant.challenge.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.challenge.dto.TransactionDTO;
import com.globant.challenge.model.Account;
import com.globant.challenge.model.Transaction;
import com.globant.challenge.repository.AccountRepository;
import com.globant.challenge.repository.TransactionRepository;

@Service
public class TransactionService {

	private static final Logger LOG = LoggerFactory.getLogger(TransactionService.class);

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private AccountRepository accountRepository;

	public List<TransactionDTO> findAll() {
		
		List<TransactionDTO> transactionsDTO = new ArrayList<>();

		Iterable<Transaction> transactions = transactionRepository.findAll();

		transactions.forEach(transaction -> {
			TransactionDTO transactionDTO = new TransactionDTO();

			transactionDTO.setTransactionId(transaction.getTransactionId());
			transactionDTO.setAccountId(transaction.getAccount().getAccountId());
			transactionDTO.setDate(transaction.getDate());
			transactionDTO.setTransactionType(transaction.getTransactionType());
			transactionDTO.setAmount(transaction.getAmount());
			transactionDTO.setBalance(transaction.getBalance());
			
			transactionsDTO.add(transactionDTO);
		});

		return transactionsDTO;
	}

	public String createTransaction(TransactionDTO transactionDTO) {

		LOG.debug("fetching account with Id {}, ", transactionDTO.getAccountId());

		Optional<Account> account = accountRepository.findById(transactionDTO.getAccountId());

		LOG.debug("Account number {} Customer {}", account.get().getAccountNumber(), account.get().getCustomer().getPerson().getName());

		LOG.debug("Fetching last transaction");

		Transaction lastTransaction = transactionRepository.findFirstByAccountOrderByDateDesc(account.get());

		LOG.debug("Actual balance {} for account {}", lastTransaction.getBalance() , account.get().getAccountNumber());

		if(transactionDTO.getAmount() == 0) {
			return "No se permiten movimientos sin valor";
		}

		String transactionType = transactionType(transactionDTO.getAmount());

		if( transactionType.equals("Retiro") && lastTransaction.getBalance() == 0 ) {
			return "Saldo no disponible";
		}

		if(transactionType.equals("Retiro") ) {
			Double newBalance = lastTransaction.getAmount() + transactionDTO.getAmount();
			if( newBalance < 0 ) {
				return "El valor el retiro supera el saldo disponible";
			}
		}
		
		Transaction transaction = new Transaction();

		transaction.setTransactionType(transactionType);
		transaction.setDate(new Date());
		transaction.setAmount(transactionDTO.getAmount());
		transaction.setBalance( lastTransaction.getBalance() + transactionDTO.getAmount() );

		transaction.setAccount(account.get());
	
		transaction = transactionRepository.save(transaction);

		return String.format("Transaction created sucessfully with id %s and new balance %s", transaction.getTransactionId(), transaction.getBalance());
	}

	/**
	 * Create initial Transaction for create Account
	 * 
	 * @param transactionDTO
	 * @param account
	 * @return
	 */
	public String createTransaction(TransactionDTO transactionDTO, Account account) {

		Transaction transaction = new Transaction();

		transaction.setTransactionType(transactionDTO.getTransactionType());;
		transaction.setDate(transactionDTO.getDate());
		transaction.setAmount(transactionDTO.getAmount());
		transaction.setBalance(transactionDTO.getBalance());
		transaction.setAccount(account);

		transaction = transactionRepository.save(transaction);

		return String.format("Transaction created sucessfully with id %s", transaction.getTransactionId());
	}

	private String transactionType(Double amount) {
		if( amount > 0 ) {
			return "Deposito";
		}else {
			return "Retiro";
		}
	}
}
