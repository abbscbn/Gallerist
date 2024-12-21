package com.abbascoban.controller;

import com.abbascoban.dto.DtoAccount;
import com.abbascoban.dto.DtoAccounttUI;

public interface IRestAccountController {
	
	public RootEntity<DtoAccount> saveAccount(DtoAccounttUI dtoAccounttUI);

}
