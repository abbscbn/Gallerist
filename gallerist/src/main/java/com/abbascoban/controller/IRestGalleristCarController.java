package com.abbascoban.controller;

import com.abbascoban.dto.DtoGalleristCar;
import com.abbascoban.dto.DtoGalleristCarUI;

public interface IRestGalleristCarController {
	
	public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarUI dtoGalleristCarUI);

}
