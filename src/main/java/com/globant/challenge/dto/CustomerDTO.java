package com.globant.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Data
@JsonRootName("Cliente")
public class CustomerDTO {

	@JsonProperty("person_id")
	private Long personId;

	@JsonProperty("customer_id")
	private Long customerId;

	@JsonProperty("Identificación")
	private String identificationNumber;

	@JsonProperty("Nombre")
	private String name;
	
	@JsonProperty("Dirección")
	private String address;
	
	@JsonProperty("Teléfono")
	private String phoneNumber;
	
	@JsonProperty("Edad")
	private Integer age;
	
	@JsonProperty("Género")
	private String gender;
	
	@JsonProperty("Contraseña")
	private String password;
	
	@JsonProperty("Estado")
	private Boolean status;
}
