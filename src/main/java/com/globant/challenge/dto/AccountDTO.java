package com.globant.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Data
@JsonRootName("Cuenta")
public class AccountDTO {

	@JsonProperty("account_id")
	private Long accountId;

	@JsonProperty("customer_id")
	private Long customerId;
	
	@JsonProperty("Número cuenta")
	private String accountNumber;

	@JsonProperty("Tipo")
	private String accountType;

	@JsonProperty("Saldo Inicial")
	private Double initialBalance;

	@JsonProperty("Estado")
	private Boolean status;
}
