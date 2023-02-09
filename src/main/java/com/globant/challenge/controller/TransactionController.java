package com.globant.challenge.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globant.challenge.dto.TransactionDTO;
import com.globant.challenge.model.Transaction;
import com.globant.challenge.service.TransactionService;

@RestController
@RequestMapping("movimientos")
public class TransactionController {

	private static final Logger LOG = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private TransactionService transactionService;

	@GetMapping("movimientos")
	public List<TransactionDTO> findAll() {
		return transactionService.findAll();
	}

	@PostMapping("movimiento")
	public String createTransaction(@RequestBody TransactionDTO transactionDTO) {
		return transactionService.createTransaction(transactionDTO);
	}
}
