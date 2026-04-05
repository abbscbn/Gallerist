package com.abbascoban.gallerist.service;

import com.abbascoban.gallerist.dto.DtoAddress;
import com.abbascoban.gallerist.dto.DtoAddressUI;

import java.util.List;

public interface IAddressService {

    public DtoAddress saveAddress(DtoAddressUI dtoAddressUI);

    public DtoAddress updateAddress(DtoAddress dtoAddress);

    public List<DtoAddress> getAllAddress();

    public String deleteAddress(Long addressId);

}
