package com.abbascoban.gallerist.controller.Impl;

import com.abbascoban.gallerist.controller.IRestCurrencyRatesController;
import com.abbascoban.gallerist.controller.RestBaseController;
import com.abbascoban.gallerist.controller.RootEntity;
import com.abbascoban.gallerist.dto.CurrencyRatesResponse;
import com.abbascoban.gallerist.service.ICurrencyRatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/rest/api")
@RequiredArgsConstructor
public class RestCurrencyRatesControllerImpl extends RestBaseController implements IRestCurrencyRatesController {


    private final ICurrencyRatesService currencyRatesService;

    @GetMapping("/currency-rates")
    @Override
    public RootEntity<List<CurrencyRatesResponse>> getCurrencyRates(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        return null;
    }

}

