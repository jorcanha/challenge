package com.globant.challenge.repository;

import org.springframework.data.repository.CrudRepository;

import com.globant.challenge.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

}
