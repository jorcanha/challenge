package com.globant.challenge.repository;

import org.springframework.data.repository.CrudRepository;

import com.globant.challenge.model.Person;

public interface PersonRepository extends CrudRepository <Person, Long> {

}
