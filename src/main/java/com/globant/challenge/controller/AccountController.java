package com.globant.challenge.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.globant.challenge.dto.AccountDTO;
import com.globant.challenge.service.AccountService;

@RestController
@RequestMapping("cuentas")
public class AccountController {

	private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	@GetMapping("cuentas")
	public @ResponseBody List<AccountDTO> findAll() {
		return accountService.findAll();
	}

	@PostMapping("cuenta")
	public String createAccount(@RequestBody AccountDTO accountDTO) {
		return accountService.createAccount(accountDTO);
	}
}
