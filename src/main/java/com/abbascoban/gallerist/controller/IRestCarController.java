package com.abbascoban.gallerist.controller;


import com.abbascoban.gallerist.dto.DtoCar;
import com.abbascoban.gallerist.dto.DtoCarDeleteReq;
import com.abbascoban.gallerist.dto.DtoCarUI;

import java.io.IOException;
import java.util.List;

public interface IRestCarController {

    public RootEntity<DtoCar> saveCar(DtoCarUI dtoCarUI) throws IOException;

    public RootEntity<DtoCar> updateCar(DtoCarUI dtoCarUI);

    public RootEntity<List<DtoCar>> getAllCar();

    public RootEntity<String> deleteCar(DtoCarDeleteReq dtoCarDeleteReq);

}

