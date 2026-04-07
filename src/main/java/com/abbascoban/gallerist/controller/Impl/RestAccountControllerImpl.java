package com.abbascoban.gallerist.controller.Impl;

import com.abbascoban.gallerist.controller.IRestAccountController;
import com.abbascoban.gallerist.controller.RestBaseController;
import com.abbascoban.gallerist.controller.RootEntity;
import com.abbascoban.gallerist.dto.DtoAccount;
import com.abbascoban.gallerist.dto.DtoAccountDeleteReq;
import com.abbascoban.gallerist.dto.DtoAccounttUI;
import com.abbascoban.gallerist.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/rest/api/account")
@RequiredArgsConstructor
public class RestAccountControllerImpl extends RestBaseController implements IRestAccountController {


    private final IAccountService accountService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccounttUI dtoAccounttUI) {

        return ok(accountService.saveAccount(dtoAccounttUI));
    }

    @PostMapping("/update")
    @Override
    public RootEntity<DtoAccount> updateAccount(@Valid @RequestBody DtoAccounttUI dtoAccounttUI) {
        return ok(accountService.updateAccount(dtoAccounttUI));
    }

    @GetMapping("/getallaccount")
    @Override
    public RootEntity<List<DtoAccount>> getAllAccount() {
        return ok(accountService.getAllAccount());
    }

    @DeleteMapping("/delete")
    @Override
    public RootEntity<String> deleteAccount(@RequestBody DtoAccountDeleteReq accountNo) {
        return ok(accountService.deleteAccount(accountNo));
    }


}
