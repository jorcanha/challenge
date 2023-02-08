package com.globant.challenge.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.challenge.dto.CustomerDTO;
import com.globant.challenge.model.Customer;
import com.globant.challenge.model.Person;
import com.globant.challenge.repository.CustomerRepository;
import com.globant.challenge.repository.PersonRepository;

@Service
public class CustomerService {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public List<CustomerDTO> findAll() {
		
		Iterable<Customer> customers = customerRepository.findAll();
		
		List<CustomerDTO> customersDTO = new ArrayList<CustomerDTO>();
		
		customers.forEach(customer -> {
			CustomerDTO customerDTO = new CustomerDTO();
			
			customerDTO.setPersonId(customer.getPerson().getPersonId());
			customerDTO.setIdentificationNumber(customer.getPerson().getIdentificationNumber());
			customerDTO.setName(customer.getPerson().getName());
			customerDTO.setAddress(customer.getPerson().getAddress());
			customerDTO.setPhoneNumber(customer.getPerson().getPhoneNumber());
			customerDTO.setAge(customer.getPerson().getAge());
			customerDTO.setGender(customer.getPerson().getGender());
			
			customerDTO.setCustomerId(customer.getCustomerId());
			customerDTO.setStatus(customer.getStatus());
			customerDTO.setPassword(customer.getPassword());
	
			customersDTO.add(customerDTO);
		});
		
		return customersDTO;
	}
	
	public String createCustomer(CustomerDTO customerDTO) {
		
		Person newPerson = new Person();
		newPerson.setIdentificationNumber(customerDTO.getIdentificationNumber());
		newPerson.setName(customerDTO.getName());
		newPerson.setAddress(customerDTO.getAddress());
		newPerson.setPhoneNumber(customerDTO.getPhoneNumber());
		newPerson.setAge(customerDTO.getAge());
		newPerson.setGender(customerDTO.getGender());
		
		LOG.debug("Creating new Person: {}", newPerson);
		newPerson = personRepository.save(newPerson);
		
		Customer newCustomer = new Customer();
		
		newCustomer.setPerson(newPerson);
		newCustomer.setPassword(customerDTO.getPassword());
		newCustomer.setStatus(customerDTO.getStatus());
		
		LOG.debug("Creating new Customer: {}", newCustomer);
		newCustomer = customerRepository.save(newCustomer);
		
		return String.format("New Customer created sucessfully, perso_id %s, customer_id %s", newPerson.getPersonId() , newCustomer.getCustomerId() );
	}
}
