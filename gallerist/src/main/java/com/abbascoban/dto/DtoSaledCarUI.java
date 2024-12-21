package com.abbascoban.dto;

import com.abbascoban.model.Car;
import com.abbascoban.model.Customer;
import com.abbascoban.model.Gallerist;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSaledCarUI {
	
	@NotNull
	private Long customerId;
	@NotNull
	private Long galleristId;
	@NotNull
	private Long carId;
	
	
	
	

}
