package com.abbascoban.dto;

import java.math.BigDecimal;

import com.abbascoban.enums.CurrencyType;
import com.abbascoban.model.BaseEntity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAccount extends DtoBase {
	
	
	private String accountNo;
	
	
	private String iban;
	
	
	private BigDecimal amount;
	
	
	
	private CurrencyType currencyType;

}
