package com.abbascoban.gallerist.controller;

import com.abbascoban.gallerist.dto.DtoGalleristCar;
import com.abbascoban.gallerist.dto.DtoGalleristCarDeleteReq;
import com.abbascoban.gallerist.dto.DtoGalleristCarUI;

import java.util.List;

public interface IRestGalleristCarController {

    public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarUI dtoGalleristCarUI);

    public RootEntity<DtoGalleristCar> updateGalleristCar(DtoGalleristCarUI dtoGalleristCarUI);

    public RootEntity<List<DtoGalleristCar>> gelAllGalleristCar();

    public RootEntity<String> deleteGalleristCar(Long galleristCarId);

    public RootEntity<DtoGalleristCar> getGalleristCarById(Long id);

    public RootEntity<List<DtoGalleristCar>> getGalleristCarsByGalleristId();



}

