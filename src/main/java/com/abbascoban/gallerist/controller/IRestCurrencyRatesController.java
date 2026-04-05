package com.abbascoban.gallerist.controller;

import com.abbascoban.gallerist.dto.CurrencyRatesResponse;

import java.util.List;

public interface IRestCurrencyRatesController {

    public RootEntity<List<CurrencyRatesResponse>> getCurrencyRates(String startDate, String endDate);


}
