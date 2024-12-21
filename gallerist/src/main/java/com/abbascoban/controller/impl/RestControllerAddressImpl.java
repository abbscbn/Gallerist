package com.abbascoban.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abbascoban.controller.IRestAddressController;
import com.abbascoban.controller.RestBaseController;
import com.abbascoban.controller.RootEntity;
import com.abbascoban.dto.DtoAddress;
import com.abbascoban.dto.DtoAddressUI;
import com.abbascoban.service.IAddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/address")
public class RestControllerAddressImpl extends RestBaseController implements IRestAddressController {
	
	@Autowired
	private IAddressService addressService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoAddress> savedAddress(@Valid @RequestBody DtoAddressUI dtoAddressUI) {
		return ok(addressService.saveAddress(dtoAddressUI));
	}

	

}
