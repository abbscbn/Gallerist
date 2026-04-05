package com.abbascoban.gallerist.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.abbascoban.gallerist.dto.DtoAccount;
import com.abbascoban.gallerist.dto.DtoAccountDeleteReq;
import com.abbascoban.gallerist.dto.DtoAccounttUI;
import com.abbascoban.gallerist.exception.BaseException;
import com.abbascoban.gallerist.exception.ErrorMessage;
import com.abbascoban.gallerist.exception.MessageType;
import com.abbascoban.gallerist.model.Account;
import com.abbascoban.gallerist.repository.AccountRepository;
import com.abbascoban.gallerist.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;


@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {


    private final AccountRepository accountRepository;


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

    @Override
    public DtoAccount updateAccount(DtoAccounttUI dtoAccounttUI) {

        DtoAccount dtoAccount= new DtoAccount();


        Optional<Account> optAccountByAccountNo = accountRepository.findByaccountNo(dtoAccounttUI.getAccountNo());

        if(optAccountByAccountNo.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST,""));
        }
        optAccountByAccountNo.get().setIban(dtoAccounttUI.getIban());
        optAccountByAccountNo.get().setAmount(dtoAccounttUI.getAmount());

        Account updatedAccount = accountRepository.save(optAccountByAccountNo.get());

        BeanUtils.copyProperties(updatedAccount,dtoAccount);

        return dtoAccount;

    }

    @Override
    public List<DtoAccount> getAllAccount() {

        Sort sort= Sort.by(Sort.Direction.ASC,"id");
        List<DtoAccount> dtoAccountList = new ArrayList<>();
        List<Account> allAccount = accountRepository.findAll(sort);

        allAccount.forEach(account -> {

            DtoAccount dtoAccount= new DtoAccount();
            BeanUtils.copyProperties(account,dtoAccount);
            dtoAccountList.add(dtoAccount);

        } );


        return dtoAccountList;
    }

    @Override
    public String deleteAccount(DtoAccountDeleteReq accountNo) {

        Optional<Account> OptAccountByAccountNo = accountRepository.findByaccountNo(accountNo.getAccountNo());

        if(OptAccountByAccountNo.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST,""));
        }

        accountRepository.deleteById(OptAccountByAccountNo.get().getId());

        return "Success";
    }

}
