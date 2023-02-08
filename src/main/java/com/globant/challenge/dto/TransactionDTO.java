package com.globant.challenge.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Data
@JsonRootName("Movimiento")
public class TransactionDTO {

	@JsonProperty("transaction_id")
	private Long transactionId;
	
	@JsonProperty("account_id")
	private Long accountId;
	
	@JsonProperty("Tipo movimiento")
	private String transactionType;
		
	@JsonProperty("Fecha")
	private Date date;

	@JsonProperty("Valor")
	private Double amount;

	@JsonProperty("Saldo")
	private Double balance;
}
