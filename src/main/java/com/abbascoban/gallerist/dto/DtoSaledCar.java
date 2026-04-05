package com.abbascoban.gallerist.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSaledCar extends DtoBase {

    private DtoCustomer customer;

    private DtoGalleristCar galleristCar;



}
