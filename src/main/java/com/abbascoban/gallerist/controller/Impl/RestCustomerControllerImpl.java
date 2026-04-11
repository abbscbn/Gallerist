package com.abbascoban.gallerist.controller.Impl;

import com.abbascoban.gallerist.controller.IRestCustomerController;
import com.abbascoban.gallerist.controller.RestBaseController;
import com.abbascoban.gallerist.controller.RootEntity;
import com.abbascoban.gallerist.dto.DtoCustomer;
import com.abbascoban.gallerist.dto.DtoCustomerDeleteReq;
import com.abbascoban.gallerist.dto.DtoCustomerUI;
import com.abbascoban.gallerist.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rest/api/customer")
@RequiredArgsConstructor
public class RestCustomerControllerImpl extends RestBaseController implements IRestCustomerController {


    private final ICustomerService customerService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerUI dtoCustomerUI) {

        return ok(customerService.saveCustomer(dtoCustomerUI));

    }

    @PutMapping( value = "/update", consumes = "multipart/form-data")
    @Override
    public RootEntity<DtoCustomer> updateCustomer(@Valid @ModelAttribute DtoCustomerUI dtoCustomerUI) throws IOException {
        return ok(customerService.updateCustomer(dtoCustomerUI));
    }

    @GetMapping("/getallcustomer")
    @Override
    public RootEntity<List<DtoCustomer>> getAllCustomer() {
        return ok(customerService.getAllCustomer());
    }

    @DeleteMapping("/delete")
    @Override
    public RootEntity<String> deleteCustomer(@RequestBody DtoCustomerDeleteReq dtoCustomerDeleteReq) {
        return ok(customerService.deleteCustomer(dtoCustomerDeleteReq));
    }

    @GetMapping("/me")
    @Override
    public RootEntity<DtoCustomer> getCustomer() {
        return ok(customerService.getCustomer());
    }

}
