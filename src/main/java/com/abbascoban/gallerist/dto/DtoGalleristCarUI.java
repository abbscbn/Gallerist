package com.abbascoban.gallerist.dto;

import com.abbascoban.gallerist.enums.CarStatusType;
import com.abbascoban.gallerist.enums.CurrencyType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoGalleristCarUI extends DtoBase {


    @NotNull
    private Long caId;

    private String plaka;

    private BigDecimal price;


    private CurrencyType currencyType;


    private BigDecimal damagePrice;


    private CarStatusType carStatusType;



}
