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

import com.globant.challenge.dto.CustomerDTO;
import com.globant.challenge.model.Customer;
import com.globant.challenge.service.CustomerService;

@RestController
@RequestMapping("clientes")
public class CustomerController {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@GetMapping("clientes")
	public @ResponseBody List<CustomerDTO> findAll() {
		return customerService.findAll();
	}

	@PostMapping("cliente")
	public String createCustomer(@RequestBody CustomerDTO customerDTO) {
		LOG.debug("Creating new Customer: {}", customerDTO.toString());
		return customerService.createCustomer(customerDTO);
	}
}
