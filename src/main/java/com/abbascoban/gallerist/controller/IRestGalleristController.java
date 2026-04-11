package com.abbascoban.gallerist.controller;

import com.abbascoban.gallerist.dto.DtoGallerist;
import com.abbascoban.gallerist.dto.DtoGalleristDeleteReq;
import com.abbascoban.gallerist.dto.DtoGalleristUI;

import java.io.IOException;
import java.util.List;

public interface IRestGalleristController {

    public RootEntity<DtoGallerist> saveGallerist(DtoGalleristUI dtoGalleristUI);

    public RootEntity<DtoGallerist> updateGallerist(DtoGalleristUI dtoGalleristUI) throws IOException;

    public RootEntity<List<DtoGallerist>> getAllGallerist();

    public RootEntity<String> deletegallerist(DtoGalleristDeleteReq dtoGalleristDeleteReq);

    public RootEntity<DtoGallerist> getGallerist();

}
