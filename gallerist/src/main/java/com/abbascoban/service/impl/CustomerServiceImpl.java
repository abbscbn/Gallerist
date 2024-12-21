package com.abbascoban.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abbascoban.dto.DtoAccount;
import com.abbascoban.dto.DtoAddress;
import com.abbascoban.dto.DtoCustomer;
import com.abbascoban.dto.DtoCustomerUI;
import com.abbascoban.exception.BaseException;
import com.abbascoban.exception.ErrorMessage;
import com.abbascoban.exception.MessageType;
import com.abbascoban.model.Account;
import com.abbascoban.model.Address;
import com.abbascoban.model.Customer;
import com.abbascoban.repository.AccountRepository;
import com.abbascoban.repository.AddressRepository;
import com.abbascoban.repository.CustomerRepository;
import com.abbascoban.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	
	@Autowired
	private AccountRepository accountRepository;
	
	
	
	private Customer createCustomer(DtoCustomerUI dtoCustomerUI) {
		Optional<Address> optAddress = addressRepository.findById(dtoCustomerUI.getAddressId());
		if(optAddress.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, dtoCustomerUI.getAddressId().toString()));
		}
		
		Optional<Account> optAccount = accountRepository.findById(dtoCustomerUI.getAccountId());
		if(optAccount.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, dtoCustomerUI.getAccountId().toString()));
		}
		
		Customer customer= new Customer();
		customer.setCreateTime(new Date());
		BeanUtils.copyProperties(dtoCustomerUI, customer);
		customer.setAddress(optAddress.get());
		customer.setAccount(optAccount.get());
		
		return customer;
		
		
	}

	@Override
	public DtoCustomer saveCustomer(DtoCustomerUI dtoCustomerUI) {
		DtoCustomer dtoCustomer= new DtoCustomer();
		DtoAddress dtoAddress= new DtoAddress();
		DtoAccount dtoAccount= new DtoAccount();
		Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerUI));
		BeanUtils.copyProperties(savedCustomer, dtoCustomer);
		BeanUtils.copyProperties(savedCustomer.getAddress(), dtoAddress);
		BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);
		dtoCustomer.setAddress(dtoAddress);
		dtoCustomer.setAccount(dtoAccount);
		return dtoCustomer;
	}
	
	

}
