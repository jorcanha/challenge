package com.globant.challenge.repository;

import org.springframework.data.repository.CrudRepository;

import com.globant.challenge.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
