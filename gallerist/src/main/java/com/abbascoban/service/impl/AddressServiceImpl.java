package com.abbascoban.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abbascoban.dto.DtoAddress;
import com.abbascoban.dto.DtoAddressUI;
import com.abbascoban.exception.BaseException;
import com.abbascoban.exception.ErrorMessage;
import com.abbascoban.exception.MessageType;
import com.abbascoban.model.Address;
import com.abbascoban.repository.AddressRepository;
import com.abbascoban.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {
	
	@Autowired
	private AddressRepository addressRepository;

	
	private Address createAddress(DtoAddressUI dtoAddressUI) {
		
		Address address= new Address();
		address.setCreateTime(new Date());
		BeanUtils.copyProperties(dtoAddressUI, address);
		return address;
		
		
		
		
	}
	
	@Override
	public DtoAddress saveAddress(DtoAddressUI dtoAddressUI) {
		
		 Address savedAddress = addressRepository.save(createAddress(dtoAddressUI));
		 DtoAddress dtoAddress= new DtoAddress();
		 BeanUtils.copyProperties(savedAddress, dtoAddress);
		 return dtoAddress;
	}
	
	
}
