package com.abbascoban.gallerist.service;

import com.abbascoban.gallerist.dto.DtoAccount;
import com.abbascoban.gallerist.dto.DtoAccountDeleteReq;
import com.abbascoban.gallerist.dto.DtoAccounttUI;

import java.util.List;


public interface IAccountService {


    public DtoAccount saveAccount(DtoAccounttUI dtoAccounttUI);

    public DtoAccount updateAccount(DtoAccounttUI dtoAccounttUI);

    public List<DtoAccount> getAllAccount();

    public String deleteAccount(DtoAccountDeleteReq accountNo);


}

