package com.abbascoban.gallerist.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.abbascoban.gallerist.dto.*;
import com.abbascoban.gallerist.exception.BaseException;
import com.abbascoban.gallerist.exception.ErrorMessage;
import com.abbascoban.gallerist.exception.MessageType;
import com.abbascoban.gallerist.model.Car;
import com.abbascoban.gallerist.model.Gallerist;
import com.abbascoban.gallerist.model.GalleristCar;
import com.abbascoban.gallerist.repository.CarRepository;
import com.abbascoban.gallerist.repository.GalleristCarRepository;
import com.abbascoban.gallerist.repository.GalleristRepository;
import com.abbascoban.gallerist.service.IGalleristCarService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class GalleristCarServiceImpl implements IGalleristCarService {


    private final GalleristRepository galleristRepository;


    private final CarRepository carRepository;


    private final GalleristCarRepository galleristCarRepository;


    private GalleristCar createGalleristCar(DtoGalleristCarUI dtoGalleristCarUI) {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        Gallerist gallerist = galleristRepository.findByUser_Username(username).orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, "")));



        GalleristCar galleristCar= new GalleristCar();


        Optional<Car> optCar = carRepository.findById(dtoGalleristCarUI.getCaId());

        if(optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, ""));
        }

        BeanUtils.copyProperties(dtoGalleristCarUI,galleristCar);
        galleristCar.setCreateTime(new Date());
        galleristCar.setCar(optCar.get());
        galleristCar.setGallerist(gallerist);

        return galleristCar;


    }

    @PreAuthorize("hasAnyRole('ADMIN','GALLERIST')")
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

    @PreAuthorize("hasRole('ADMIN','GALLERIST')")
    @Override
    public DtoGalleristCar updateGalleristCar(DtoGalleristCarUI dtoGalleristCarUI) {

        Optional<GalleristCar> optGalleristCar = galleristCarRepository.findById(dtoGalleristCarUI.getId());

        if(optGalleristCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST,""));
        }

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        Gallerist gallerist = galleristRepository.findByUser_Username(username).orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, "")));

        Optional<Car> optCar = carRepository.findById(dtoGalleristCarUI.getCaId());

        if(optCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST,""));

        }
            // Server-Side kontrol sağlamak için frontend ve backend uyuşuyor mu diye
        if(!gallerist.getUser().getUsername().equals(optGalleristCar.get().getGallerist().getUser().getUsername())){

            throw new BaseException(new ErrorMessage(MessageType.UNAUTHORIZED_ACCESS_ERROR,""));
        }



        BeanUtils.copyProperties(dtoGalleristCarUI,optGalleristCar.get());
        optGalleristCar.get().setGallerist(gallerist);
        optGalleristCar.get().setCar(optCar.get());

        GalleristCar updatedGalleristCar = galleristCarRepository.save(optGalleristCar.get());

        DtoGalleristCar dtoGalleristCar= new DtoGalleristCar();
        DtoGallerist dtoGallerist= new DtoGallerist();
        DtoCar dtoCar= new DtoCar();

        BeanUtils.copyProperties(updatedGalleristCar.getGallerist(),dtoGallerist);
        BeanUtils.copyProperties(updatedGalleristCar.getCar(),dtoCar);

        BeanUtils.copyProperties(updatedGalleristCar,dtoGalleristCar);

        dtoGalleristCar.setCar(dtoCar);
        dtoGalleristCar.setGallerist(dtoGallerist);

        return dtoGalleristCar;
    }

    @Override
    public List<DtoGalleristCar> gelAllGalleristCar() {
        List<DtoGalleristCar> dtoGalleristCarList= new ArrayList<>();
        Sort sort= Sort.by(Sort.Direction.ASC,"id");
        List<GalleristCar> allGalleristCar = galleristCarRepository.findAll(sort);

        allGalleristCar.forEach(galleristCar -> {

            DtoGallerist dtoGallerist= new DtoGallerist();
            DtoCar dtoCar= new DtoCar();
            DtoGalleristCar dtoGalleristCar= new DtoGalleristCar();

            BeanUtils.copyProperties(galleristCar.getGallerist(),dtoGallerist);
            BeanUtils.copyProperties(galleristCar.getCar(),dtoCar);

            BeanUtils.copyProperties(galleristCar,dtoGalleristCar);

            dtoGalleristCar.setGallerist(dtoGallerist);
            dtoGalleristCar.setCar(dtoCar);

            dtoGalleristCarList.add(dtoGalleristCar);

        });

        return dtoGalleristCarList;
    }

    @PreAuthorize("hasRole('ADMIN','GALLERIST')")
    @Override
    public String deleteGalleristCar(DtoGalleristCarDeleteReq dtoGalleristCarDeleteReq) {

        Optional<GalleristCar> optGalleristCar = galleristCarRepository.findById(dtoGalleristCarDeleteReq.getGalleristCarId());

        if(optGalleristCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST,""));
        }

       try {
           galleristCarRepository.deleteById(dtoGalleristCarDeleteReq.getGalleristCarId());
       } catch (Exception e) {
           throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION,e.getMessage()));
       }

        return "Success";
    }

    @Override
    public DtoGalleristCar getGalleristCarById(Long id) {

        GalleristCar galleristCar = galleristCarRepository.findById(id).orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, "")));

        DtoGallerist dtoGallerist= new DtoGallerist();
        DtoCar dtoCar= new DtoCar();
        DtoGalleristCar dtoGalleristCar= new DtoGalleristCar();

        BeanUtils.copyProperties(galleristCar.getGallerist(),dtoGallerist);
        BeanUtils.copyProperties(galleristCar.getCar(),dtoCar);

        BeanUtils.copyProperties(galleristCar,dtoGalleristCar);

        dtoGalleristCar.setGallerist(dtoGallerist);
        dtoGalleristCar.setCar(dtoCar);

        return dtoGalleristCar;
    }

}
