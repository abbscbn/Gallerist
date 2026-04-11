package com.abbascoban.gallerist.controller;


import com.abbascoban.gallerist.dto.DtoCustomer;
import com.abbascoban.gallerist.dto.DtoCustomerDeleteReq;
import com.abbascoban.gallerist.dto.DtoCustomerUI;

import java.io.IOException;
import java.util.List;

public interface IRestCustomerController {

    public RootEntity<DtoCustomer> saveCustomer(DtoCustomerUI dtoCustomerUI);

    public RootEntity<DtoCustomer> updateCustomer(DtoCustomerUI dtoCustomerUI) throws IOException;

    public RootEntity<List<DtoCustomer>> getAllCustomer();

    public RootEntity<String> deleteCustomer(DtoCustomerDeleteReq dtoCustomerDeleteReq);

    public RootEntity<DtoCustomer> getCustomer();

}
