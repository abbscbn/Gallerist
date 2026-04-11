package com.abbascoban.gallerist.service;


import com.abbascoban.gallerist.dto.DtoGalleristCar;
import com.abbascoban.gallerist.dto.DtoGalleristCarDeleteReq;
import com.abbascoban.gallerist.dto.DtoGalleristCarUI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IGalleristCarService {

    public DtoGalleristCar saveGalleristCar(DtoGalleristCarUI dtoGalleristCarUI);

    public DtoGalleristCar updateGalleristCar(DtoGalleristCarUI dtoGalleristCarUI);

    public Page<DtoGalleristCar> getAllGalleristCar(Pageable pageable);

    public String deleteGalleristCar(Long galleristCarId);

    public DtoGalleristCar getGalleristCarById(Long id);

    public List<DtoGalleristCar> getGalleristCarsByGalleristId();

    public Page<DtoGalleristCar> filter(String brand,
                                        String model,
                                        Pageable pageable);



}
