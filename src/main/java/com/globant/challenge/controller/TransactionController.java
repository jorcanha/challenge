package com.globant.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globant.challenge.model.Transaction;
import com.globant.challenge.service.TransactionService;

@RestController
@RequestMapping("movimientos")
public class TransactionController {

	private static final Logger LOG = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private TransactionService transactionService;

	@GetMapping("movimientos")
	public Iterable<Transaction> findAll() {
		return transactionService.findAll();
	}
	
}
