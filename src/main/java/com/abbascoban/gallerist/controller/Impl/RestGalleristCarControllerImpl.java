package com.abbascoban.gallerist.controller.Impl;

import com.abbascoban.gallerist.controller.IRestGalleristCarController;
import com.abbascoban.gallerist.controller.RestBaseController;
import com.abbascoban.gallerist.controller.RootEntity;
import com.abbascoban.gallerist.dto.DtoGalleristCar;
import com.abbascoban.gallerist.dto.DtoGalleristCarDeleteReq;
import com.abbascoban.gallerist.dto.DtoGalleristCarUI;
import com.abbascoban.gallerist.service.IGalleristCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/rest/api/gallerist-car")
@RequiredArgsConstructor
public class RestGalleristCarControllerImpl extends RestBaseController implements IRestGalleristCarController {


    private final IGalleristCarService galleristCarService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarUI dtoGalleristCarUI) {

        return ok(galleristCarService.saveGalleristCar(dtoGalleristCarUI));
    }

    @PutMapping("/update")
    @Override
    public RootEntity<DtoGalleristCar> updateGalleristCar(@Valid @RequestBody DtoGalleristCarUI dtoGalleristCarUI) {
        return ok(galleristCarService.updateGalleristCar(dtoGalleristCarUI));
    }

    @GetMapping("/getallgalleristcar")
    public RootEntity<Page<DtoGalleristCar>> getAllGalleristCar(
            @PageableDefault(size = 6,page = 0,sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable) {

        return ok(galleristCarService.getAllGalleristCar(pageable));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public RootEntity<String> deleteGalleristCar(@PathVariable(name = "id") Long galleristCarId) {
        return ok(galleristCarService.deleteGalleristCar(galleristCarId));
    }

    @GetMapping("/get/{id}")
    @Override
    public RootEntity<DtoGalleristCar> getGalleristCarById(@PathVariable(name = "id") Long id) {
        return ok(galleristCarService.getGalleristCarById(id));
    }

    @GetMapping("/me")
    @Override
    public RootEntity<List<DtoGalleristCar>> getGalleristCarsByGalleristId() {
        return ok(galleristCarService.getGalleristCarsByGalleristId());
    }
    @GetMapping("/filter")
    @Override
    public RootEntity<Page<DtoGalleristCar>> filter(@RequestParam(required = false) String brand,
                                                    @RequestParam(required = false) String model,
                                                    Pageable pageable) {

        return ok(galleristCarService.filter(brand,model,pageable));
    }

}
