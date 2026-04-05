package com.abbascoban.gallerist.controller;

import com.abbascoban.gallerist.dto.DtoAddress;
import com.abbascoban.gallerist.dto.DtoAddressDeleteReq;
import com.abbascoban.gallerist.dto.DtoAddressUI;

import java.util.List;

public interface IRestAddressController {

    public RootEntity<DtoAddress> savedAddress(DtoAddressUI dtoAddressUI);

    public RootEntity<DtoAddress> updateAddress(DtoAddress dtoAddress);

    public RootEntity<List<DtoAddress>> getAllAddress();

    public RootEntity<String> deleteAddress(DtoAddressDeleteReq dtoAddressDeleteReq);

}
