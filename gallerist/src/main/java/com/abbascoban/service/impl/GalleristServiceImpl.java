package com.abbascoban.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abbascoban.dto.DtoAddress;
import com.abbascoban.dto.DtoGallerist;
import com.abbascoban.dto.DtoGalleristUI;
import com.abbascoban.exception.BaseException;
import com.abbascoban.exception.ErrorMessage;
import com.abbascoban.exception.MessageType;
import com.abbascoban.model.Address;
import com.abbascoban.model.Gallerist;
import com.abbascoban.repository.AddressRepository;
import com.abbascoban.repository.GalleristRepository;
import com.abbascoban.service.IGalleristService;

@Service
public class GalleristServiceImpl implements IGalleristService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private GalleristRepository galleristRepository;
	
	private Gallerist createGallerist(DtoGalleristUI dtoGalleristUI) {
		
		Gallerist gallerist= new Gallerist();
		gallerist.setCreateTime(new Date());
		BeanUtils.copyProperties(dtoGalleristUI, gallerist);
		Optional<Address> optAddress = addressRepository.findById(dtoGalleristUI.getAddressId());
		
		if(optAddress.isEmpty()) {
			
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, dtoGalleristUI.getAddressId().toString()));
			
		}
		
		gallerist.setAddress(optAddress.get());
		return gallerist;
		
		
		
	}

	@Override
	public DtoGallerist saveGallerist(DtoGalleristUI dtoGalleristUI) {
		DtoGallerist dtoGallerist= new DtoGallerist();
		DtoAddress dtoAddress= new DtoAddress();
		Gallerist gallerist = createGallerist(dtoGalleristUI);
		Gallerist savedGallerist = galleristRepository.save(gallerist);
		BeanUtils.copyProperties(savedGallerist, dtoGallerist);
		BeanUtils.copyProperties(savedGallerist.getAddress(), dtoAddress);
		dtoGallerist.setAddress(dtoAddress);
		return dtoGallerist;
	}

}
