package com.abbascoban.gallerist.controller.Impl;

import com.abbascoban.gallerist.controller.IRestGalleristController;
import com.abbascoban.gallerist.controller.RestBaseController;
import com.abbascoban.gallerist.controller.RootEntity;
import com.abbascoban.gallerist.dto.DtoGallerist;
import com.abbascoban.gallerist.dto.DtoGalleristDeleteReq;
import com.abbascoban.gallerist.dto.DtoGalleristUI;
import com.abbascoban.gallerist.service.IGalleristService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/rest/api/gallerist")
@RequiredArgsConstructor
public class RestGalleristControllerImpl extends RestBaseController implements IRestGalleristController {


    private final IGalleristService galleristService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoGallerist> saveGallerist(@Valid @RequestBody DtoGalleristUI dtoGalleristUI) {

        return ok(galleristService.saveGallerist(dtoGalleristUI));


    }

    @PostMapping("/update")
    @Override
    public RootEntity<DtoGallerist> updateGallerist(@Valid @RequestBody DtoGalleristUI dtoGalleristUI) {

        return ok(galleristService.updateGallerist(dtoGalleristUI));
    }

    @GetMapping("/me")
    @Override
    public RootEntity<DtoGallerist> getGallerist() {

        return ok(galleristService.getGallerist());
    }

    @GetMapping("/gelallgallerist")
    @Override
    public RootEntity<List<DtoGallerist>> getAllGallerist() {
        return ok(galleristService.getAllGallerist());
    }

    @DeleteMapping("/delete")
    @Override
    public RootEntity<String> deletegallerist(@RequestBody DtoGalleristDeleteReq dtoGalleristDeleteReq) {
        return ok(galleristService.deletegallerist(dtoGalleristDeleteReq));
    }

}
