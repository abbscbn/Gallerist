package com.abbascoban.gallerist.service;


import com.abbascoban.gallerist.dto.DtoSaledCar;
import com.abbascoban.gallerist.dto.DtoSaledCarUI;

public interface ISaledCarService {

    public DtoSaledCar buyCar(Long galleristCarId);

}