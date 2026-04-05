package com.abbascoban.gallerist.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.abbascoban.gallerist.dto.DtoAddress;
import com.abbascoban.gallerist.dto.DtoAddressUI;
import com.abbascoban.gallerist.exception.BaseException;
import com.abbascoban.gallerist.exception.ErrorMessage;
import com.abbascoban.gallerist.exception.MessageType;
import com.abbascoban.gallerist.model.Address;
import com.abbascoban.gallerist.repository.AddressRepository;
import com.abbascoban.gallerist.repository.UserRepository;
import com.abbascoban.gallerist.service.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;


@PreAuthorize("hasAnyRole('ADMIN','GALLERIST','CUSTOMER')")
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements IAddressService {


    private final AddressRepository addressRepository;




    private Address createAddress(DtoAddressUI dtoAddressUI) {

        Address address= new Address();
        address.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoAddressUI, address);
        return address;


    }

    @Override
    public DtoAddress saveAddress(DtoAddressUI dtoAddressUI) {

        Address savedAddress = addressRepository.save(createAddress(dtoAddressUI));
        DtoAddress dtoAddress= new DtoAddress();
        BeanUtils.copyProperties(savedAddress, dtoAddress);
        return dtoAddress;
    }

    @Override
    public DtoAddress updateAddress(DtoAddress dtoAddressUI) {

        DtoAddress dtoAddress= new DtoAddress();

        Optional<Address> optAdress = addressRepository.findById(dtoAddressUI.getId());

        if(optAdress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST,""));
        }

        BeanUtils.copyProperties(dtoAddressUI,optAdress.get());
        optAdress.get().setCreateTime(new Date());
        Address updatedAddress = addressRepository.save(optAdress.get());

        BeanUtils.copyProperties(updatedAddress,dtoAddress);

        return dtoAddress;
    }

    @Override
    public List<DtoAddress> getAllAddress() {
        List<DtoAddress> dtoAddresses= new ArrayList<>();

        Sort sort= Sort.by(Sort.Direction.ASC,"id");

        List<Address> allAddress = addressRepository.findAll(sort);

        allAddress.forEach(address -> {

            DtoAddress dtoAddress= new DtoAddress();

            BeanUtils.copyProperties(address,dtoAddress);

            dtoAddresses.add(dtoAddress);

        });

        return dtoAddresses;
    }

    @Override
    public String deleteAddress(Long addressId) {

        Optional<Address> optAddress = addressRepository.findById(addressId);

        if(optAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST,""));
        }

        addressRepository.deleteById(addressId);

        return "Success";
    }


}
