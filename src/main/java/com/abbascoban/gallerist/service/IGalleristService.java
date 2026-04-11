package com.abbascoban.gallerist.service;


import com.abbascoban.gallerist.dto.DtoCustomerUI;
import com.abbascoban.gallerist.dto.DtoGallerist;
import com.abbascoban.gallerist.dto.DtoGalleristDeleteReq;
import com.abbascoban.gallerist.dto.DtoGalleristUI;

import java.io.IOException;
import java.util.List;

public interface IGalleristService {

    public DtoGallerist saveGallerist(DtoGalleristUI dtoGalleristUI);

    public DtoGallerist updateGallerist(DtoGalleristUI dtoGalleristUI) throws IOException;

    public List<DtoGallerist> getAllGallerist();

    public String deletegallerist(DtoGalleristDeleteReq dtoGalleristDeleteReq);

    public DtoGallerist getGallerist();


}
