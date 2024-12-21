package com.abbascoban.controller;

import com.abbascoban.dto.DtoCar;
import com.abbascoban.dto.DtoCarUI;

public interface IRestCarController {
	
	public RootEntity<DtoCar> saveCar(DtoCarUI dtoCarUI);

}
