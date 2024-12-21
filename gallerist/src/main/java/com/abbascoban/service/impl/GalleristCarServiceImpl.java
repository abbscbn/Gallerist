package com.abbascoban.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abbascoban.dto.DtoAddress;
import com.abbascoban.dto.DtoCar;
import com.abbascoban.dto.DtoGallerist;
import com.abbascoban.dto.DtoGalleristCar;
import com.abbascoban.dto.DtoGalleristCarUI;
import com.abbascoban.exception.BaseException;
import com.abbascoban.exception.ErrorMessage;
import com.abbascoban.exception.MessageType;
import com.abbascoban.model.Car;
import com.abbascoban.model.Gallerist;
import com.abbascoban.model.GalleristCar;
import com.abbascoban.repository.CarRepository;
import com.abbascoban.repository.GalleristCarRepository;
import com.abbascoban.repository.GalleristRepository;
import com.abbascoban.service.IGalleristCarService;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService {
	
	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private GalleristCarRepository galleristCarRepository;
	
	private GalleristCar createGalleristCar(DtoGalleristCarUI dtoGalleristCarUI) {
		
		GalleristCar galleristCar= new GalleristCar();
		galleristCar.setCreateTime(new Date());
		
		Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristCarUI.getGalleristId());
		Optional<Car> optCar = carRepository.findById(dtoGalleristCarUI.getCaId());
		
		if(optGallerist.isEmpty() && optCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, dtoGalleristCarUI.getCaId().toString()+" "+dtoGalleristCarUI.getGalleristId()));
		}
		
		galleristCar.setCar(optCar.get());
		galleristCar.setGallerist(optGallerist.get());
		
		return galleristCar;
		
		
	}

	@Override
	public DtoGalleristCar saveGalleristCar(DtoGalleristCarUI dtoGalleristCarUI) {
		DtoGalleristCar dtoGalleristCar= new DtoGalleristCar();
		DtoGallerist dtoGallerist= new DtoGallerist();
		DtoCar dtoCar= new DtoCar();
		DtoAddress dtoAddress= new DtoAddress();
		
		GalleristCar galleristCar = createGalleristCar(dtoGalleristCarUI);
		GalleristCar savedGalleristCar = galleristCarRepository.save(galleristCar);
		
		BeanUtils.copyProperties(savedGalleristCar, dtoGalleristCar);
		BeanUtils.copyProperties(savedGalleristCar.getGallerist(), dtoGallerist);
		BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(), dtoAddress);
		dtoGallerist.setAddress(dtoAddress);
		
		BeanUtils.copyProperties(savedGalleristCar.getCar(), dtoCar);
		
		dtoGalleristCar.setCar(dtoCar);
		dtoGalleristCar.setGallerist(dtoGallerist);
		
		
		return dtoGalleristCar;
	}

}
