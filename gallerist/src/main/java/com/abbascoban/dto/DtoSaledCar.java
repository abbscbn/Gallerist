package com.abbascoban.dto;

import com.abbascoban.model.BaseEntity;
import com.abbascoban.model.Car;
import com.abbascoban.model.Customer;
import com.abbascoban.model.Gallerist;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DtoSaledCar extends DtoBase {
	
	private DtoCustomer customer;
	
	private DtoGallerist gallerist;
	
	private DtoCar car;
	
	
	
	

}
