package com.abbascoban.gallerist.service.impl;

import java.util.*;

import com.abbascoban.gallerist.dto.*;
import com.abbascoban.gallerist.exception.BaseException;
import com.abbascoban.gallerist.exception.ErrorMessage;
import com.abbascoban.gallerist.exception.MessageType;
import com.abbascoban.gallerist.model.Account;
import com.abbascoban.gallerist.model.Address;
import com.abbascoban.gallerist.model.Customer;
import com.abbascoban.gallerist.model.User;
import com.abbascoban.gallerist.repository.AccountRepository;
import com.abbascoban.gallerist.repository.AddressRepository;
import com.abbascoban.gallerist.repository.CustomerRepository;
import com.abbascoban.gallerist.repository.UserRepository;
import com.abbascoban.gallerist.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {


    private final CustomerRepository customerRepository;


    private final AddressRepository addressRepository;

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;



    private Customer createCustomer(DtoCustomerUI dtoCustomerUI) {
        Optional<Address> optAddress = addressRepository.findById(dtoCustomerUI.getAddressId());
        if(optAddress.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, dtoCustomerUI.getAddressId().toString()));
        }

        Optional<Account> optAccount = accountRepository.findById(dtoCustomerUI.getAccountId());
        if(optAccount.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, dtoCustomerUI.getAccountId().toString()));
        }

        Optional<User> optUser = userRepository.findById(dtoCustomerUI.getUserId());

        if(optUser.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, dtoCustomerUI.getAccountId().toString()));

        }



        Customer customer= new Customer();

        BeanUtils.copyProperties(dtoCustomerUI, customer);
        customer.setAddress(optAddress.get());
        customer.setAccount(optAccount.get());
        customer.setUser(optUser.get());
        customer.setCreateTime(new Date());
        return customer;


    }

    @Override
    public DtoCustomer saveCustomer(DtoCustomerUI dtoCustomerUI) {
        DtoCustomer dtoCustomer= new DtoCustomer();
        DtoAddress dtoAddress= new DtoAddress();
        DtoAccount dtoAccount= new DtoAccount();
        DtoUser dtoUser= new DtoUser();
        Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerUI));
        BeanUtils.copyProperties(savedCustomer, dtoCustomer);
        BeanUtils.copyProperties(savedCustomer.getAddress(), dtoAddress);
        BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);
        BeanUtils.copyProperties(savedCustomer.getUser(), dtoUser);
        dtoCustomer.setAddress(dtoAddress);
        dtoCustomer.setAccount(dtoAccount);
        dtoCustomer.setUser(dtoUser);
        return dtoCustomer;
    }

    @Override
    public DtoCustomer updateCustomer(DtoCustomerUI dtoCustomerUI) {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        Customer customer = customerRepository.findByUser_Username(username).orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, "")));


        Optional<Address> optAddress = addressRepository.findById(dtoCustomerUI.getAddressId());

        if(optAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST,""));
        }

        Optional<Account> optAccount = accountRepository.findById(dtoCustomerUI.getAccountId());

        if(optAccount.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST,""));
        }
        User user= customer.getUser(); // bağlı user değişmesin diye direk mevcutu atıyorum tekrardan
        Long customerId = customer.getId();
        BeanUtils.copyProperties(dtoCustomerUI,customer);
        customer.setId(customerId);
        customer.setCreateTime(new Date());
        customer.setAddress(optAddress.get());
        customer.setAccount(optAccount.get());
        customer.setUser(user);

        Customer updatedCustomer = customerRepository.save(customer);
        user.setIsProfileCompleted(true);
        userRepository.save(user);

        DtoAddress dtoAddress= new DtoAddress();
        DtoAccount dtoAccount= new DtoAccount();
        DtoUser dtoUser = new DtoUser();

        BeanUtils.copyProperties(optAddress.get(),dtoAddress);
        BeanUtils.copyProperties(optAccount.get(),dtoAccount);
        BeanUtils.copyProperties(user,dtoUser);


        DtoCustomer dtoCustomer= new DtoCustomer();

        BeanUtils.copyProperties(updatedCustomer,dtoCustomer);

        dtoCustomer.setAddress(dtoAddress);
        dtoCustomer.setAccount(dtoAccount);
        dtoCustomer.setUser(dtoUser);





        return dtoCustomer;
    }

    @Override
    public List<DtoCustomer> getAllCustomer() {

        List<DtoCustomer> dtoCustomerList= new ArrayList<>();

        Sort sort= Sort.by(Sort.Direction.ASC,"id");

        List<Customer> allCustomer = customerRepository.findAll(sort);

        allCustomer.forEach(customer -> {

            DtoCustomer dtoCustomer= new DtoCustomer();
            DtoAddress dtoAddress= new DtoAddress();
            DtoAccount dtoAccount = new DtoAccount();
            DtoUser dtoUser= new DtoUser();

            BeanUtils.copyProperties(customer.getAddress(),dtoAddress);
            BeanUtils.copyProperties(customer.getAccount(),dtoAccount);
            BeanUtils.copyProperties(customer.getUser(),dtoUser);

            BeanUtils.copyProperties(customer,dtoCustomer);

            dtoCustomer.setAddress(dtoAddress);
            dtoCustomer.setAccount(dtoAccount);
            dtoCustomer.setUser(dtoUser);

            dtoCustomerList.add(dtoCustomer);


        });

        return dtoCustomerList;
    }

    @Override
    public String deleteCustomer(DtoCustomerDeleteReq dtoCustomerDeleteReq) {

        Optional<Customer> optCustomer = customerRepository.findById(dtoCustomerDeleteReq.getCustomerId());

        if(optCustomer.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, ""));

        }

        Long userId= optCustomer.get().getUser().getId();

        customerRepository.deleteById(dtoCustomerDeleteReq.getCustomerId());

        userRepository.deleteById(userId);

        return "Success";
    }

    @Override
    public DtoCustomer getCustomer() {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        Customer customer = customerRepository.findByUser_Username(username).orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, "")));



        DtoCustomer dtoCustomer= new DtoCustomer();
        DtoAddress dtoAddress= new DtoAddress();
        DtoAccount dtoAccount = new DtoAccount();
        DtoUser dtoUser= new DtoUser();

        BeanUtils.copyProperties(customer.getAddress(),dtoAddress);
        BeanUtils.copyProperties(customer.getAccount(),dtoAccount);
        BeanUtils.copyProperties(customer.getUser(),dtoUser);

        BeanUtils.copyProperties(customer,dtoCustomer);

        dtoCustomer.setAddress(dtoAddress);
        dtoCustomer.setAccount(dtoAccount);
        dtoCustomer.setUser(dtoUser);

        return dtoCustomer;
    }


}
