package com.abbascoban.gallerist.dto;


import com.abbascoban.gallerist.enums.CarStatusType;
import com.abbascoban.gallerist.enums.CurrencyType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DtoGalleristCar extends DtoBase {

    private DtoGallerist gallerist;

    private DtoCar car;

    @NotNull
    private String plaka;

    @NotNull
    private BigDecimal price;

    @NotNull
    private CurrencyType currencyType;

    @NotNull
    private BigDecimal damagePrice;

    @NotNull
    private CarStatusType carStatusType;
}
