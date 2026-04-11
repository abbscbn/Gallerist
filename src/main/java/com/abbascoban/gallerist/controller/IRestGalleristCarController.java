package com.abbascoban.gallerist.controller;

import com.abbascoban.gallerist.dto.DtoGalleristCar;
import com.abbascoban.gallerist.dto.DtoGalleristCarDeleteReq;
import com.abbascoban.gallerist.dto.DtoGalleristCarUI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRestGalleristCarController {

    public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarUI dtoGalleristCarUI);

    public RootEntity<DtoGalleristCar> updateGalleristCar(DtoGalleristCarUI dtoGalleristCarUI);

    public RootEntity<Page<DtoGalleristCar>> getAllGalleristCar(Pageable pageable);

    public RootEntity<String> deleteGalleristCar(Long galleristCarId);

    public RootEntity<DtoGalleristCar> getGalleristCarById(Long id);

    public RootEntity<List<DtoGalleristCar>> getGalleristCarsByGalleristId();

    public RootEntity<Page<DtoGalleristCar>> filter(String brand, String model, Pageable pageable);



}

