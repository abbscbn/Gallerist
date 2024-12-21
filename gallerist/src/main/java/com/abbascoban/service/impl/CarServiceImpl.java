package com.abbascoban.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abbascoban.dto.DtoCar;
import com.abbascoban.dto.DtoCarUI;
import com.abbascoban.model.Car;
import com.abbascoban.repository.CarRepository;
import com.abbascoban.service.ICarService;

@Service
public class CarServiceImpl  implements ICarService{
	
	@Autowired
	private CarRepository carRepository;
	
	private Car createCar(DtoCarUI dtoCarUI) {
		
		Car car= new Car();
		car.setCreateTime(new Date());
		BeanUtils.copyProperties(dtoCarUI, car);
		return car;
		
	}

	@Override
	public DtoCar saveCar(DtoCarUI dtoCarUI) {
		DtoCar dtoCar= new DtoCar();
		Car car = createCar(dtoCarUI);
		Car savedCar = carRepository.save(car);
		BeanUtils.copyProperties(savedCar, dtoCar);
		return dtoCar;
	}

}
