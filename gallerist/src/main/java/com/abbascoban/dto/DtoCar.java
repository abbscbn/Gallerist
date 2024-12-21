package com.abbascoban.dto;

import java.math.BigDecimal;

import com.abbascoban.enums.CarStatusType;
import com.abbascoban.enums.CurrencyType;
import com.abbascoban.model.BaseEntity;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCar extends BaseEntity{
	
	@NotNull
	private String plaka;
	
	@NotNull
	private String brand;
	
	@NotNull
	private String model;
	
	@NotNull
	private Integer productionYear;
	
	@NotNull
	private BigDecimal price;
	
	@NotNull
	private CurrencyType currencyType;
	
	@NotNull
	private BigDecimal damagePrice;
	
	@NotNull
	private CarStatusType carStatusType;

}
