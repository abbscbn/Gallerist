package com.abbascoban.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abbascoban.controller.IRestGalleristCarController;
import com.abbascoban.controller.RestBaseController;
import com.abbascoban.controller.RootEntity;
import com.abbascoban.dto.DtoGalleristCar;
import com.abbascoban.dto.DtoGalleristCarUI;
import com.abbascoban.repository.GalleristCarRepository;
import com.abbascoban.service.IGalleristCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist-car")
public class RestGalleristCarControllerImpl extends RestBaseController implements IRestGalleristCarController {
	
	@Autowired
	private IGalleristCarService galleristCarService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarUI dtoGalleristCarUI) {
		
		return ok(galleristCarService.saveGalleristCar(dtoGalleristCarUI));
	}

}
