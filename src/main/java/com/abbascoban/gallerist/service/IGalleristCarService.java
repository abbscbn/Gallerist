package com.abbascoban.gallerist.service;


import com.abbascoban.gallerist.dto.DtoGalleristCar;
import com.abbascoban.gallerist.dto.DtoGalleristCarDeleteReq;
import com.abbascoban.gallerist.dto.DtoGalleristCarUI;

import java.util.List;

public interface IGalleristCarService {

    public DtoGalleristCar saveGalleristCar(DtoGalleristCarUI dtoGalleristCarUI);

    public DtoGalleristCar updateGalleristCar(DtoGalleristCarUI dtoGalleristCarUI);

    public List<DtoGalleristCar> gelAllGalleristCar();

    public String deleteGalleristCar(DtoGalleristCarDeleteReq dtoGalleristCarDeleteReq);



}
