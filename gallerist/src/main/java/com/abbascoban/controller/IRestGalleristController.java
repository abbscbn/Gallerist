package com.abbascoban.controller;

import com.abbascoban.dto.DtoGallerist;
import com.abbascoban.dto.DtoGalleristUI;

public interface IRestGalleristController {
	
	public RootEntity<DtoGallerist> saveGallerist(DtoGalleristUI dtoGalleristUI);

}
