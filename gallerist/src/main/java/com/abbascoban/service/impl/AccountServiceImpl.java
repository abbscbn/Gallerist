package com.abbascoban.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abbascoban.dto.DtoAccount;
import com.abbascoban.dto.DtoAccounttUI;
import com.abbascoban.model.Account;
import com.abbascoban.repository.AccountRepository;
import com.abbascoban.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	
	private Account createAccount(DtoAccounttUI dtoAccounttUI) {
		
		Account account= new Account();
		
		account.setCreateTime(new Date());
		BeanUtils.copyProperties(dtoAccounttUI, account);
		return account;
		
	}
	
	@Override
	public DtoAccount saveAccount(DtoAccounttUI dtoAccounttUI) {
		Account savedAccount = accountRepository.save(createAccount(dtoAccounttUI));
		DtoAccount dtoAccount=new DtoAccount();
		BeanUtils.copyProperties(savedAccount, dtoAccount);
		return dtoAccount;
	}

}
