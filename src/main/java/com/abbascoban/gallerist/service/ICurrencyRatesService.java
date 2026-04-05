package com.abbascoban.gallerist.service;


import com.abbascoban.gallerist.dto.CurrencyRatesResponse;

import java.util.List;

public interface ICurrencyRatesService {

    public List<CurrencyRatesResponse> getCurrencyRates(String startDate, String endDate);

}