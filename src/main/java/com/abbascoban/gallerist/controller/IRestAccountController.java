package com.abbascoban.gallerist.controller;

import com.abbascoban.gallerist.dto.DtoAccount;
import com.abbascoban.gallerist.dto.DtoAccountDeleteReq;
import com.abbascoban.gallerist.dto.DtoAccounttUI;

import java.util.List;

public interface IRestAccountController {

    public RootEntity<DtoAccount> saveAccount(DtoAccounttUI dtoAccounttUI);

    public RootEntity<DtoAccount> updateAccount(DtoAccounttUI dtoAccounttUI);

    public RootEntity<List<DtoAccount>> getAllAccount();

    public RootEntity<String> deleteAccount(DtoAccountDeleteReq accountNo);

}
