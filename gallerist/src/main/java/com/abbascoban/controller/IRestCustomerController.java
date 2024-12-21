package com.abbascoban.controller;

import com.abbascoban.dto.DtoCustomer;
import com.abbascoban.dto.DtoCustomerUI;

public interface IRestCustomerController {
	
	public RootEntity<DtoCustomer> saveCustomer(DtoCustomerUI dtoCustomerUI);

}
