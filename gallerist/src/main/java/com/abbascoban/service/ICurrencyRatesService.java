package com.abbascoban.service;

import com.abbascoban.dto.CurrencyRatesResponse;

public interface ICurrencyRatesService {
	
	public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate);

}
