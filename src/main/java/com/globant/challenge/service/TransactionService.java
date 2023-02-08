package com.globant.challenge.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.challenge.dto.TransactionDTO;
import com.globant.challenge.model.Account;
import com.globant.challenge.model.Transaction;
import com.globant.challenge.repository.TransactionRepository;

@Service
public class TransactionService {

	private static final Logger LOG = LoggerFactory.getLogger(TransactionService.class);

	@Autowired
	private TransactionRepository transactionRepository;
	
	public Iterable<Transaction> findAll() {
		return transactionRepository.findAll();
	}
	
	public String createTransaction(TransactionDTO transactionDTO, Account account) {
		
		Transaction transaction = new Transaction();
		
		transaction.setTransaction_type(transactionDTO.getTransaction_type());;
		transaction.setDate(transactionDTO.getDate());
		transaction.setAmount(transactionDTO.getAmount());
		transaction.setBalance(transactionDTO.getBalance());
		transaction.setAccount(account);
		
		transaction = transactionRepository.save(transaction);
		
		return String.format("Transaction created sucessfully with id %s", transaction.getTransactionId());
	}
}
