package com.abbascoban.gallerist.controller.Impl;

import com.abbascoban.gallerist.controller.IRestCarController;
import com.abbascoban.gallerist.controller.RestBaseController;
import com.abbascoban.gallerist.controller.RootEntity;
import com.abbascoban.gallerist.dto.DtoCar;
import com.abbascoban.gallerist.dto.DtoCarDeleteReq;
import com.abbascoban.gallerist.dto.DtoCarUI;
import com.abbascoban.gallerist.model.Car;
import com.abbascoban.gallerist.service.ICarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rest/api/car")
@RequiredArgsConstructor
public class RestCarControllerImpl extends RestBaseController implements IRestCarController {


    private final ICarService carService;

    @PostMapping(value = "/save", consumes = "multipart/form-data")
    @Override
    public RootEntity<DtoCar> saveCar(@Valid @ModelAttribute DtoCarUI dtoCarUI) throws IOException {
        return ok(carService.saveCar(dtoCarUI));
    }

    @PutMapping(value = "/update", consumes = "multipart/form-data")
    @Override
    public RootEntity<DtoCar> updateCar(@Valid @ModelAttribute DtoCarUI dtoCarUI) {
        return ok(carService.updateCar(dtoCarUI));
    }

    @GetMapping("/getallcar")
    @Override
    public RootEntity<List<DtoCar>> getAllCar() {
       return ok(carService.getAllCar());
    }

    @DeleteMapping("/delete")
    @Override
    public RootEntity<String> deleteCar(@RequestBody DtoCarDeleteReq dtoCarDeleteReq) {
        return ok(carService.deleteCar(dtoCarDeleteReq));
    }


//    @GetMapping("/getsaledcar")
//    public RootEntity<List<Car>> getSaledCarList(){
//        return ok(carService.getSaledCarList());
//    }

}