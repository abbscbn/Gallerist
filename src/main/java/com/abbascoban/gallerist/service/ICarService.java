package com.abbascoban.gallerist.service;


import com.abbascoban.gallerist.dto.DtoCar;
import com.abbascoban.gallerist.dto.DtoCarDeleteReq;
import com.abbascoban.gallerist.dto.DtoCarUI;
import com.abbascoban.gallerist.model.Car;

import java.io.IOException;
import java.util.List;

public interface ICarService {

    public DtoCar saveCar(DtoCarUI dtoCarUI) throws IOException;

//    List<Car> getSaledCarList();

    public DtoCar updateCar(DtoCarUI dtoCarUI);

    public List<DtoCar> getAllCar();

    public String deleteCar(DtoCarDeleteReq dtoCarDeleteReq);



}
