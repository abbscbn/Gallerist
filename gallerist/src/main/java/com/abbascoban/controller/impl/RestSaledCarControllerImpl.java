package com.abbascoban.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abbascoban.controller.IRestSaledCarController;
import com.abbascoban.controller.RestBaseController;
import com.abbascoban.controller.RootEntity;
import com.abbascoban.dto.DtoSaledCar;
import com.abbascoban.dto.DtoSaledCarUI;
import com.abbascoban.service.ISaledCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/saled-car")
public class RestSaledCarControllerImpl extends RestBaseController implements IRestSaledCarController{
	
	@Autowired
	private ISaledCarService saledCarService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoSaledCar> buyCar(@Valid @RequestBody DtoSaledCarUI dtoSaledCarUI) {
		
		return ok(saledCarService.buyCar(dtoSaledCarUI));
	}
	
	

}
