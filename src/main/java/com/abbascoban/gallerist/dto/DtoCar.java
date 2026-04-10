package com.abbascoban.gallerist.dto;

import java.math.BigDecimal;


import com.abbascoban.gallerist.enums.CarStatusType;
import com.abbascoban.gallerist.enums.CurrencyType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCar extends DtoBase{



    @NotNull
    private String brand;

    @NotNull
    private String model;

    @NotNull
    private Integer productionYear;

    private String imageUrl;



}
