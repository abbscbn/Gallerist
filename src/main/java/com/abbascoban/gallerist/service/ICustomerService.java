package com.abbascoban.gallerist.service;


import com.abbascoban.gallerist.dto.DtoCustomer;
import com.abbascoban.gallerist.dto.DtoCustomerDeleteReq;
import com.abbascoban.gallerist.dto.DtoCustomerUI;

import java.util.List;

public interface ICustomerService {

    public DtoCustomer saveCustomer(DtoCustomerUI dtoCustomerUI);

    public DtoCustomer updateCustomer(DtoCustomerUI dtoCustomerUI);

    public List<DtoCustomer> getAllCustomer();

    public String deleteCustomer(DtoCustomerDeleteReq dtoCustomerDeleteReq);

    public DtoCustomer getCustomer();


}
