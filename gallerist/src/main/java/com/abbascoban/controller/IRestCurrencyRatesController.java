package com.abbascoban.controller;

import com.abbascoban.dto.CurrencyRatesResponse;

public interface IRestCurrencyRatesController {
	
	public RootEntity<CurrencyRatesResponse> getCurrencyRates(String startDate, String endDate);
	

}
