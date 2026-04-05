package com.abbascoban.gallerist.controller.Impl;

import com.abbascoban.gallerist.controller.IRestSaledCarController;
import com.abbascoban.gallerist.controller.RestBaseController;
import com.abbascoban.gallerist.controller.RootEntity;
import com.abbascoban.gallerist.dto.DtoSaledCar;
import com.abbascoban.gallerist.dto.DtoSaledCarUI;
import com.abbascoban.gallerist.service.ISaledCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/saled-car")
@RequiredArgsConstructor
public class RestSaledCarControllerImpl extends RestBaseController implements IRestSaledCarController {


    private final ISaledCarService saledCarService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoSaledCar> buyCar(@Valid @RequestBody DtoSaledCarUI dtoSaledCarUI) {

        return ok(saledCarService.buyCar(dtoSaledCarUI));
    }



}
