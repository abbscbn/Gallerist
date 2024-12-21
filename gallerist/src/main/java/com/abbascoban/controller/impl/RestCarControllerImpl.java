package com.abbascoban.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abbascoban.controller.IRestCarController;
import com.abbascoban.controller.RestBaseController;
import com.abbascoban.controller.RootEntity;
import com.abbascoban.dto.DtoCar;
import com.abbascoban.dto.DtoCarUI;
import com.abbascoban.service.ICarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/car")
public class RestCarControllerImpl extends RestBaseController implements IRestCarController {
	
	@Autowired
	private ICarService carService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoCar> saveCar(@Valid @RequestBody DtoCarUI dtoCarUI) {
		return ok(carService.saveCar(dtoCarUI));
	}

}
