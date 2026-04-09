package com.abbascoban.gallerist.controller;

import com.abbascoban.gallerist.dto.DtoSaledCar;
import com.abbascoban.gallerist.dto.DtoSaledCarUI;

public interface IRestSaledCarController {

    public RootEntity<DtoSaledCar> buyCar(Long galleristCarId);

}
