package com.abbascoban.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abbascoban.dto.CurrencyRatesResponse;
import com.abbascoban.dto.DtoCar;
import com.abbascoban.dto.DtoCustomer;
import com.abbascoban.dto.DtoGallerist;
import com.abbascoban.dto.DtoSaledCar;
import com.abbascoban.dto.DtoSaledCarUI;
import com.abbascoban.enums.CarStatusType;
import com.abbascoban.exception.BaseException;
import com.abbascoban.exception.ErrorMessage;
import com.abbascoban.exception.MessageType;
import com.abbascoban.model.Car;
import com.abbascoban.model.Customer;
import com.abbascoban.model.Gallerist;
import com.abbascoban.model.SaledCar;
import com.abbascoban.repository.CarRepository;
import com.abbascoban.repository.CustomerRepository;
import com.abbascoban.repository.GalleristRepository;
import com.abbascoban.repository.SaledCarRepository;
import com.abbascoban.service.ICurrencyRatesService;
import com.abbascoban.service.ISaledCarService;
import com.abbascoban.utils.DateUtils;

@Service
public class SaledCarServiceImpl implements ISaledCarService {
	
	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ICurrencyRatesService currencyRatesService;
	
	@Autowired
	private SaledCarRepository saledCarRepository;

	private Car byId;
	
	
	public BigDecimal convertCustomerAmountToUSD(Customer customer) {
		
		CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
		
		BigDecimal usd=new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
		
		BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd,2,RoundingMode.HALF_UP);
		
		return customerUSDAmount;
		
		
	}
	
	
	
	public boolean checkAmount(DtoSaledCarUI dtoSaledCarUI) {
		
		Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarUI.getCustomerId());
		Optional<Car> optCar = carRepository.findById(dtoSaledCarUI.getCarId());
		
		if(optCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, dtoSaledCarUI.getCarId().toString()));
		}
		if(optCustomer.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, dtoSaledCarUI.getCustomerId().toString()));
		}
		
		BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optCustomer.get());
		
		if(customerUSDAmount.compareTo(optCar.get().getPrice()) == 0 || customerUSDAmount.compareTo(optCar.get().getPrice()) > 0 ) {
			
			return true;
		}
		
		return false;
		
		
	}
	
	private SaledCar createSaledCar(DtoSaledCarUI dtoSaledCarUI) {
		
		SaledCar saledCar= new SaledCar();
		saledCar.setCreateTime(new Date());
		Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarUI.getCustomerId());
		Optional<Car> optCar = carRepository.findById(dtoSaledCarUI.getCarId());
		Optional<Gallerist> optGallerist = galleristRepository.findById(dtoSaledCarUI.getGalleristId());
		if(optGallerist.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, dtoSaledCarUI.getGalleristId().toString()));
		}
		
		saledCar.setCustomer(optCustomer.get());
		saledCar.setCar(optCar.get());
		saledCar.setGallerist(optGallerist.get());
		
		return saledCar;
		
		
	}
	
	public boolean checkCarStatus(Long carId) {
		Optional<Car> optCar = carRepository.findById(carId);
		
		if(optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name())) {
			
			return false;
			
		}
		
		return true;
		
		
		
	}
	
	public BigDecimal remaningCustomerAmount(Customer customer, Car car) {
		
		BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);
		BigDecimal remaningCustomerUSDAmount=customerUSDAmount.subtract(car.getPrice());
		
		CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
		
		BigDecimal usd= new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
		
		return remaningCustomerUSDAmount.multiply(usd);
		
	}
	
	
	public DtoSaledCar toDTO(SaledCar saledCar) {
		
		DtoSaledCar dtoSaledCar= new DtoSaledCar();
		
		DtoCustomer dtoCustomer= new DtoCustomer();
		
		DtoCar dtoCar= new DtoCar();
		
		DtoGallerist dtoGallerist= new DtoGallerist();
		
		BeanUtils.copyProperties(saledCar, dtoSaledCar);
		BeanUtils.copyProperties(saledCar.getCustomer(), dtoCustomer);
		BeanUtils.copyProperties(saledCar.getCar(), dtoCar);
		BeanUtils.copyProperties(saledCar.getGallerist(), dtoGallerist);
		
		dtoSaledCar.setCar(dtoCar);
		dtoSaledCar.setCustomer(dtoCustomer);
		dtoSaledCar.setGallerist(dtoGallerist);
		
		return dtoSaledCar;
		
		
		
	}
	
	
	
	
	@Override
	public DtoSaledCar buyCar(DtoSaledCarUI dtoSaledCarUI) {
		
		
		
		if(!checkCarStatus(dtoSaledCarUI.getCarId())) {
			throw new BaseException(new ErrorMessage(MessageType.CAR_STATUS_IS_ALREADY_SALED, dtoSaledCarUI.getCarId().toString()));
		}
		
		if(!checkAmount(dtoSaledCarUI)) {
			throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, dtoSaledCarUI.getCustomerId().toString()));
		}
		
		SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarUI));
		
		Car car = savedSaledCar.getCar();
		car.setCarStatusType(CarStatusType.SALED);
		carRepository.save(car);
		
		Customer customer = savedSaledCar.getCustomer();
		customer.getAccount().setAmount(remaningCustomerAmount(customer, car));
		customerRepository.save(customer);
		
		
		return toDTO(savedSaledCar);
	}

}
