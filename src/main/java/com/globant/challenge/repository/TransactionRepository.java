package com.globant.challenge.repository;

import org.springframework.data.repository.CrudRepository;

import com.globant.challenge.model.Account;
import com.globant.challenge.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long>  {

	Transaction findFirstByAccountOrderByDateDesc(Account account);
}
