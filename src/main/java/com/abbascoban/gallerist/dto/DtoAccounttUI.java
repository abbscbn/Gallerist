package com.abbascoban.gallerist.dto;
import java.math.BigDecimal;


import com.abbascoban.gallerist.enums.CurrencyType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DtoAccounttUI {

    @NotNull
    private String accountNo;

    @NotNull
    private String iban;

    @NotNull
    private BigDecimal amount;


    @NotNull
    private CurrencyType currencyType;


}