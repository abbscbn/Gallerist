package com.abbascoban.controller;

import com.abbascoban.dto.DtoAddress;
import com.abbascoban.dto.DtoAddressUI;

public interface IRestAddressController {
	
	public RootEntity<DtoAddress> savedAddress(DtoAddressUI dtoAddressUI);

}
