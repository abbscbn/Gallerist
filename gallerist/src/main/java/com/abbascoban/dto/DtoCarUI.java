package com.abbascoban.dto;

import java.math.BigDecimal;

import com.abbascoban.enums.CarStatusType;
import com.abbascoban.enums.CurrencyType;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoCarUI {
	
	
	private String plaka;
	
	
	private String brand;
	
	
	private String model;
	
	
	private Integer productionYear;
	
	
	private BigDecimal price;
	
	
	private CurrencyType currencyType;
	
	
	private BigDecimal damagePrice;
	
	
	private CarStatusType carStatusType;

}
