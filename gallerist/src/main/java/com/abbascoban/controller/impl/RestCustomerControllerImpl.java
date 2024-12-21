package com.abbascoban.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abbascoban.controller.IRestCustomerController;
import com.abbascoban.controller.RestBaseController;
import com.abbascoban.controller.RootEntity;
import com.abbascoban.dto.DtoCustomer;
import com.abbascoban.dto.DtoCustomerUI;
import com.abbascoban.service.ICustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/customer")
public class RestCustomerControllerImpl extends RestBaseController implements IRestCustomerController {
	
	@Autowired
	private ICustomerService customerService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerUI dtoCustomerUI) {
		
		return ok(customerService.saveCustomer(dtoCustomerUI));
		
	}

}
