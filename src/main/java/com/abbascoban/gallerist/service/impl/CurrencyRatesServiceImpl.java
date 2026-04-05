package com.abbascoban.gallerist.service.impl;

import com.abbascoban.gallerist.dto.CurrencyRatesResponse;
import com.abbascoban.gallerist.exception.BaseException;
import com.abbascoban.gallerist.exception.ErrorMessage;
import com.abbascoban.gallerist.exception.MessageType;
import com.abbascoban.gallerist.service.ICurrencyRatesService;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class CurrencyRatesServiceImpl implements ICurrencyRatesService {


    @Override
    public List<CurrencyRatesResponse> getCurrencyRates(String startDate, String endDate) {

        String rootURL="https://api.frankfurter.dev/v2/rates";


        String endpoint=rootURL+"?from="+startDate+"&to="+endDate+"&quotes=TRY"; //+"series="+series+"&startDate="+startDate+"&"+"endDate="+endDate+"&type="+type;


        HttpHeaders httpHeaders= new HttpHeaders();
//        httpHeaders.set("key","RoQxeaCITM");

        HttpEntity<?> httpEntity= new HttpEntity<>(httpHeaders);

        try {

            RestTemplate restTemplate= new RestTemplate();
            ResponseEntity<List<CurrencyRatesResponse>> response = restTemplate.exchange(endpoint,HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<CurrencyRatesResponse>>() {
            });

            if(response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }

        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURED, e.getMessage()));
        }

        return null;
    }
}
