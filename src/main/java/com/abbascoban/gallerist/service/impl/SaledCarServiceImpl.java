package com.abbascoban.gallerist.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.abbascoban.gallerist.dto.*;
import com.abbascoban.gallerist.enums.CarStatusType;
import com.abbascoban.gallerist.exception.BaseException;
import com.abbascoban.gallerist.exception.ErrorMessage;
import com.abbascoban.gallerist.exception.MessageType;
import com.abbascoban.gallerist.model.*;
import com.abbascoban.gallerist.repository.*;
import com.abbascoban.gallerist.service.ICurrencyRatesService;
import com.abbascoban.gallerist.service.ISaledCarService;
import com.abbascoban.gallerist.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;


@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
@Service
@RequiredArgsConstructor
public class SaledCarServiceImpl implements ISaledCarService {


   private final GalleristCarRepository galleristCarRepository;


    private final CustomerRepository customerRepository;


    private final ICurrencyRatesService currencyRatesService;


    private final SaledCarRepository saledCarRepository;




    public BigDecimal convertCustomerAmountToEUR(Customer customer) {

        List<CurrencyRatesResponse> currencyRates = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));

        BigDecimal eur= BigDecimal.valueOf(currencyRates.get(0).getRate());

        BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(eur,2,RoundingMode.HALF_UP);

        return customerUSDAmount;


    }


    public boolean checkAmount(DtoSaledCarUI dtoSaledCarUI) {

        Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarUI.getCustomerId());
        Optional<GalleristCar> optGalleristCar = galleristCarRepository.findById(dtoSaledCarUI.getGalleristCarId());


        if(optGalleristCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST,""));
        }
        if(optCustomer.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, dtoSaledCarUI.getCustomerId().toString()));
        }

        BigDecimal customerEURAmount = convertCustomerAmountToEUR(optCustomer.get());

        if(customerEURAmount.compareTo(optGalleristCar.get().getPrice()) == 0 || customerEURAmount.compareTo(optGalleristCar.get().getPrice()) > 0 ) {

            return true;
        }

        return false;


    }

    private SaledCar createSaledCar(DtoSaledCarUI dtoSaledCarUI) {

        SaledCar saledCar= new SaledCar();
        saledCar.setCreateTime(new Date());
        Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarUI.getCustomerId());
        Optional<GalleristCar> optGalleristCar = galleristCarRepository.findById(dtoSaledCarUI.getGalleristCarId());



        if(optGalleristCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, ""));
        }
        if(optCustomer.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, ""));

        }

        saledCar.setCustomer(optCustomer.get());
        saledCar.setGalleristCar(optGalleristCar.get());


        return saledCar;


    }

    public boolean checkCarStatus(Long galleristCarId) {

        Optional<GalleristCar> optGalleristCar = galleristCarRepository.findById(galleristCarId);

        if(optGalleristCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST,"GalleristCarId kontol edilemedi: "+galleristCarId.toString()));
        }

        if(optGalleristCar.get().getCarStatusType()==CarStatusType.SALED) {

            return false;

        }

        return true;



    }

    public BigDecimal remaningCustomerAmount(Customer customer, GalleristCar galleristCar) {

        BigDecimal customerEURAmount = convertCustomerAmountToEUR(customer);

        BigDecimal remaningCustomerEURAmount=customerEURAmount.subtract(galleristCar.getPrice());

        List<CurrencyRatesResponse> currencyRates = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));

        BigDecimal eur= BigDecimal.valueOf(currencyRates.get(0).getRate());

        return remaningCustomerEURAmount.multiply(eur);

    }


    public DtoSaledCar toDTO(SaledCar saledCar) {

        DtoSaledCar dtoSaledCar= new DtoSaledCar();

        DtoCustomer dtoCustomer= new DtoCustomer();

        DtoCar dtoCar= new DtoCar();

        DtoGallerist dtoGallerist= new DtoGallerist();

        DtoGalleristCar dtoGalleristCar= new DtoGalleristCar();

        BeanUtils.copyProperties(saledCar,dtoSaledCar);
        BeanUtils.copyProperties(saledCar.getCustomer(), dtoCustomer);

        BeanUtils.copyProperties(saledCar.getGalleristCar().getCar(),dtoCar);
        BeanUtils.copyProperties(saledCar.getGalleristCar().getGallerist(),dtoGallerist);
        BeanUtils.copyProperties(saledCar.getGalleristCar(),dtoGalleristCar);
        dtoGalleristCar.setCar(dtoCar);
        dtoGalleristCar.setGallerist(dtoGallerist);

        dtoSaledCar.setCustomer(dtoCustomer);
        dtoSaledCar.setGalleristCar(dtoGalleristCar);



        return dtoSaledCar;



    }

    @Override
    public DtoSaledCar buyCar(DtoSaledCarUI dtoSaledCarUI) {



        if(!checkCarStatus(dtoSaledCarUI.getGalleristCarId())) {
            throw new BaseException(new ErrorMessage(MessageType.CAR_STATUS_IS_ALREADY_SALED, dtoSaledCarUI.getGalleristCarId().toString()));
        }

        if(!checkAmount(dtoSaledCarUI)) {
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, dtoSaledCarUI.getCustomerId().toString()));
        }

        SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarUI));

        GalleristCar galleristCar = savedSaledCar.getGalleristCar();
        galleristCar.setCarStatusType(CarStatusType.SALED);
        galleristCarRepository.save(galleristCar);

        Customer customer = savedSaledCar.getCustomer();
        customer.getAccount().setAmount(remaningCustomerAmount(customer, galleristCar));
        customerRepository.save(customer);


        return toDTO(savedSaledCar);
    }






}
