package com.globant.challenge.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long accountId;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@OneToMany(mappedBy = "account")
    private Set<Transaction> transactions = new HashSet<>();

	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "account_type")
	private String accountType;

	@Column(name = "initial_balance")
	private Double initialBalance;

	@Column(name = "status")
	private Boolean status;
}
