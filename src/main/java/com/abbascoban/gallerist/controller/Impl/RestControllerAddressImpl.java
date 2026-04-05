package com.abbascoban.gallerist.controller.Impl;
import com.abbascoban.gallerist.controller.IRestAddressController;
import com.abbascoban.gallerist.controller.RestBaseController;
import com.abbascoban.gallerist.controller.RootEntity;
import com.abbascoban.gallerist.dto.DtoAddress;
import com.abbascoban.gallerist.dto.DtoAddressDeleteReq;
import com.abbascoban.gallerist.dto.DtoAddressUI;
import com.abbascoban.gallerist.service.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/rest/api/address")
@RequiredArgsConstructor
public class RestControllerAddressImpl extends RestBaseController implements IRestAddressController {


    private final IAddressService addressService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoAddress> savedAddress(@Valid @RequestBody DtoAddressUI dtoAddressUI) {
        return ok(addressService.saveAddress(dtoAddressUI));
    }

    @PostMapping("/update")
    @Override
    public RootEntity<DtoAddress> updateAddress(@Valid @RequestBody DtoAddress dtoAddress) {
        return ok(addressService.updateAddress(dtoAddress));
    }

    @GetMapping("/getalladdress")
    @Override
    public RootEntity<List<DtoAddress>> getAllAddress() {
       return ok(addressService.getAllAddress());
    }

    @DeleteMapping("/delete")
    @Override
    public RootEntity<String> deleteAddress(@RequestBody DtoAddressDeleteReq dtoAddressDeleteReq) {
        return ok(addressService.deleteAddress(dtoAddressDeleteReq.getAddressId()));
    }


}
