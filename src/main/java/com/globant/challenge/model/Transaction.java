package com.globant.challenge.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Long transactionId;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	@Column(name = "transaction_type")
	private String transactionType;
	
	@Column(name = "date")
	private Date date;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "balance")
	private Double balance;
}
