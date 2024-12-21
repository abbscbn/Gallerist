package com.abbascoban.controller;

import com.abbascoban.dto.DtoSaledCar;
import com.abbascoban.dto.DtoSaledCarUI;

public interface IRestSaledCarController {
	
	public RootEntity<DtoSaledCar> buyCar(DtoSaledCarUI dtoSaledCarUI);

}
