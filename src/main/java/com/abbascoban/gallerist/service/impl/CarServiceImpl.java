package com.abbascoban.gallerist.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.abbascoban.gallerist.dto.DtoCar;
import com.abbascoban.gallerist.dto.DtoCarDeleteReq;
import com.abbascoban.gallerist.dto.DtoCarUI;
import com.abbascoban.gallerist.enums.CarStatusType;
import com.abbascoban.gallerist.exception.BaseException;
import com.abbascoban.gallerist.exception.ErrorMessage;
import com.abbascoban.gallerist.exception.MessageType;
import com.abbascoban.gallerist.model.BaseEntity;
import com.abbascoban.gallerist.model.Car;
import com.abbascoban.gallerist.repository.CarRepository;
import com.abbascoban.gallerist.service.ICarService;
import com.abbascoban.gallerist.service.IFileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class CarServiceImpl  implements ICarService {


    private final CarRepository carRepository;

    private final IFileStorageService fileStorageService;

    private Car createCar(DtoCarUI dtoCarUI) throws IOException {

        Car car= new Car();
        BeanUtils.copyProperties(dtoCarUI, car);
        car.setCreateTime(new Date());
        String imageUrl = fileStorageService.uploadFile(dtoCarUI.getFile());
        car.setImageUrl(imageUrl);
        return car;

    }

    @Override
    public DtoCar saveCar(DtoCarUI dtoCarUI) throws IOException {
        DtoCar dtoCar= new DtoCar();
        Car car = createCar(dtoCarUI);
        Car savedCar = carRepository.save(car);
        BeanUtils.copyProperties(savedCar, dtoCar);
        return dtoCar;
    }

    @Override
    public DtoCar updateCar(DtoCarUI dtoCarUI) {
        Optional<Car> optCar = carRepository.findById(dtoCarUI.getId());

        if (optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, ""));
        }

        Car car = optCar.get();

        // 🔹 sadece değiştirilebilir alanlar
        car.setBrand(dtoCarUI.getBrand());
        car.setModel(dtoCarUI.getModel());
        car.setProductionYear(dtoCarUI.getProductionYear());

        // 🔥 IMAGE UPDATE (kritik fix)
        if (dtoCarUI.getFile() != null && !dtoCarUI.getFile().isEmpty()) {
            try {
                String imageUrl = fileStorageService.uploadFile(dtoCarUI.getFile());
                car.setImageUrl(imageUrl);
            } catch (IOException e) {
                throw new RuntimeException("Image upload failed");
            }
        }


        Car updatedCar = carRepository.save(car);

        DtoCar dtoCar = new DtoCar();
        BeanUtils.copyProperties(updatedCar, dtoCar);

        return dtoCar;
    }

    @Override
    public List<DtoCar> getAllCar() {

        List<DtoCar> dtoCarList= new ArrayList<>();

        Sort sort = Sort.by(Sort.Direction.ASC,"id");

        List<Car> allCar = carRepository.findAll(sort);

        allCar.forEach(car -> {

            DtoCar dtoCar= new DtoCar();

            BeanUtils.copyProperties(car,dtoCar);

            dtoCarList.add(dtoCar);

        });

        return dtoCarList;
    }

    @Override
    public String deleteCar(DtoCarDeleteReq dtoCarDeleteReq) {

        Optional<Car> optCar = carRepository.findById(dtoCarDeleteReq.getCarId());

        if(optCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST,""));
        }

        carRepository.deleteById(dtoCarDeleteReq.getCarId());

        return "Success";
    }


//    @Override
//    public List<Car> getSaledCarList(){
//
//        List<Car> allBycarStatusType = carRepository.findAllBycarStatusType(CarStatusType.SALED);
//
//        return allBycarStatusType;
//    }






}
