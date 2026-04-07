package com.abbascoban.gallerist.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.abbascoban.gallerist.dto.*;
import com.abbascoban.gallerist.exception.BaseException;
import com.abbascoban.gallerist.exception.ErrorMessage;
import com.abbascoban.gallerist.exception.MessageType;
import com.abbascoban.gallerist.model.Address;
import com.abbascoban.gallerist.model.Gallerist;
import com.abbascoban.gallerist.model.User;
import com.abbascoban.gallerist.repository.AddressRepository;
import com.abbascoban.gallerist.repository.GalleristRepository;
import com.abbascoban.gallerist.repository.UserRepository;
import com.abbascoban.gallerist.service.IGalleristService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GalleristServiceImpl implements IGalleristService {


    private final AddressRepository addressRepository;

    private final GalleristRepository galleristRepository;

    private final UserRepository userRepository;


    private Gallerist createGallerist(DtoGalleristUI dtoGalleristUI) {

        Optional<User> optUser = userRepository.findById(dtoGalleristUI.getUserId());

        if(optUser.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST,""));
        }


        Gallerist gallerist= new Gallerist();
        BeanUtils.copyProperties(dtoGalleristUI, gallerist);

        gallerist.setCreateTime(new Date());
        Optional<Address> optAddress = addressRepository.findById(dtoGalleristUI.getAddressId());

        if(optAddress.isEmpty()) {

            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, dtoGalleristUI.getAddressId().toString()));

        }

        gallerist.setAddress(optAddress.get());
        gallerist.setUser(optUser.get());
        return gallerist;

    }

    @Override
    public DtoGallerist saveGallerist(DtoGalleristUI dtoGalleristUI) {

        DtoGallerist dtoGallerist= new DtoGallerist();
        DtoAddress dtoAddress= new DtoAddress();
        DtoUser dtoUser= new DtoUser();

        Gallerist gallerist = createGallerist(dtoGalleristUI);
        Gallerist savedGallerist = galleristRepository.save(gallerist);
        BeanUtils.copyProperties(savedGallerist, dtoGallerist);
        BeanUtils.copyProperties(savedGallerist.getAddress(), dtoAddress);
        BeanUtils.copyProperties(savedGallerist.getUser(),dtoUser);
        dtoGallerist.setAddress(dtoAddress);
        dtoGallerist.setUser(dtoUser);
        return dtoGallerist;
    }

    @Override
    public DtoGallerist updateGallerist(DtoGalleristUI dtoGalleristUI) {


        Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristUI.getId());

        if(optGallerist.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST,""));
        }

        Optional<Address> optAddress = addressRepository.findById(dtoGalleristUI.getAddressId());

        if(optAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST,""));
        }

        Optional<User> optUser = userRepository.findById(dtoGalleristUI.getUserId());

        if(optUser.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST,""));
        }

        User user = optGallerist.get().getUser();

        BeanUtils.copyProperties(dtoGalleristUI,optGallerist.get());
        optGallerist.get().setAddress(optAddress.get());
        optGallerist.get().setUser(user); // user ı sabit kalsın diye
        optGallerist.get().setCreateTime(new Date());

        Gallerist updatedGallerist = galleristRepository.save(optGallerist.get());

        DtoAddress dtoAddress= new DtoAddress();
        DtoUser dtoUser= new DtoUser();
        DtoGallerist dtoGallerist= new DtoGallerist();

        BeanUtils.copyProperties(updatedGallerist.getAddress(),dtoAddress);
        BeanUtils.copyProperties(updatedGallerist.getUser(),dtoUser);
        BeanUtils.copyProperties(updatedGallerist,dtoGallerist);


        dtoGallerist.setAddress(dtoAddress);
        dtoGallerist.setUser(dtoUser);


        return dtoGallerist;
    }

    @Override
    public List<DtoGallerist> getAllGallerist() {

        List<DtoGallerist> dtoGalleristList= new ArrayList<>();

        Sort sort= Sort.by(Sort.Direction.ASC,"id");

        List<Gallerist> allGallerist = galleristRepository.findAll(sort);

        allGallerist.forEach(gallerist -> {

            DtoAddress dtoAddress= new DtoAddress();
            DtoUser dtoUser= new DtoUser();
            DtoGallerist dtoGallerist= new DtoGallerist();

            BeanUtils.copyProperties(gallerist.getAddress(),dtoAddress);
            BeanUtils.copyProperties(gallerist.getUser(),dtoUser);
            BeanUtils.copyProperties(gallerist,dtoGallerist);

            dtoGallerist.setAddress(dtoAddress);
            dtoGallerist.setUser(dtoUser);

            dtoGalleristList.add(dtoGallerist);

        });

        return dtoGalleristList;
    }

    @Override
    public String deletegallerist(DtoGalleristDeleteReq dtoGalleristDeleteReq) {

        Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristDeleteReq.getGalleristId());

        if(optGallerist.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST,""));
        }
        Long userId = optGallerist.get().getUser().getId();

        galleristRepository.deleteById(dtoGalleristDeleteReq.getGalleristId());

        userRepository.deleteById(userId);

        return "Success";
    }

    @Override
    public DtoGallerist getGallerist() {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        Gallerist gallerist = galleristRepository.findByUser_Username(username)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, "")));

        DtoGallerist dtoGallerist= new DtoGallerist();
        DtoAddress dtoAddress= new DtoAddress();
        DtoUser dtoUser= new DtoUser();

        BeanUtils.copyProperties(gallerist, dtoGallerist);
        BeanUtils.copyProperties(gallerist.getAddress(), dtoAddress);
        BeanUtils.copyProperties(gallerist.getUser(),dtoUser);
        dtoGallerist.setAddress(dtoAddress);
        dtoGallerist.setUser(dtoUser);
        return dtoGallerist;

    }

}

