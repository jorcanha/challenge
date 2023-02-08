package com.globant.challenge.repository;

import org.springframework.data.repository.CrudRepository;

import com.globant.challenge.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long>  {

}
