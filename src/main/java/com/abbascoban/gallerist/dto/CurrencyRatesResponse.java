package com.abbascoban.gallerist.dto;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyRatesResponse {


    @JsonProperty("date")
    private String date;

    @JsonProperty("base")
    private String base;

    @JsonProperty("quote")
    private String quote;

    @JsonProperty("rate")
    private Double rate;


//[
//    {
//        "date": "2026-03-30",
//            "base": "EUR",
//            "quote": "TRY",
//            "rate": 51.199
//    }
//]

}