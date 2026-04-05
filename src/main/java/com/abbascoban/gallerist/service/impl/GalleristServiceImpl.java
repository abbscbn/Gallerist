package com.abbascoban.gallerist.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.abbascoban.gallerist.dto.DtoAddress;
import com.abbascoban.gallerist.dto.DtoGallerist;
import com.abbascoban.gallerist.dto.DtoGalleristDeleteReq;
import com.abbascoban.gallerist.dto.DtoGalleristUI;
import com.abbascoban.gallerist.exception.BaseException;
import com.abbascoban.gallerist.exception.ErrorMessage;
import com.abbascoban.gallerist.exception.MessageType;
import com.abbascoban.gallerist.model.Address;
import com.abbascoban.gallerist.model.Gallerist;
import com.abbascoban.gallerist.repository.AddressRepository;
import com.abbascoban.gallerist.repository.GalleristRepository;
import com.abbascoban.gallerist.service.IGalleristService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@PreAuthorize("hasAnyRole('ADMIN','GALLERIST')")
@Service
@RequiredArgsConstructor
public class GalleristServiceImpl implements IGalleristService {


    private final AddressRepository addressRepository;

    private final GalleristRepository galleristRepository;

    private Gallerist createGallerist(DtoGalleristUI dtoGalleristUI) {

        Gallerist gallerist= new Gallerist();

        BeanUtils.copyProperties(dtoGalleristUI, gallerist);

        gallerist.setCreateTime(new Date());
        Optional<Address> optAddress = addressRepository.findById(dtoGalleristUI.getAddressId());

        if(optAddress.isEmpty()) {

            throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, dtoGalleristUI.getAddressId().toString()));

        }

        gallerist.setAddress(optAddress.get());
        return gallerist;



    }

    @Override
    public DtoGallerist saveGallerist(DtoGalleristUI dtoGalleristUI) {
        DtoGallerist dtoGallerist= new DtoGallerist();
        DtoAddress dtoAddress= new DtoAddress();
        Gallerist gallerist = createGallerist(dtoGalleristUI);
        Gallerist savedGallerist = galleristRepository.save(gallerist);
        BeanUtils.copyProperties(savedGallerist, dtoGallerist);
        BeanUtils.copyProperties(savedGallerist.getAddress(), dtoAddress);
        dtoGallerist.setAddress(dtoAddress);
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

        BeanUtils.copyProperties(dtoGalleristUI,optGallerist.get());
        optGallerist.get().setAddress(optAddress.get());
        optGallerist.get().setCreateTime(new Date());

        Gallerist updatedGallerist = galleristRepository.save(optGallerist.get());

        DtoAddress dtoAddress= new DtoAddress();
        DtoGallerist dtoGallerist= new DtoGallerist();

        BeanUtils.copyProperties(updatedGallerist.getAddress(),dtoAddress);
        BeanUtils.copyProperties(updatedGallerist,dtoGallerist);


        dtoGallerist.setAddress(dtoAddress);


        return dtoGallerist;
    }

    @Override
    public List<DtoGallerist> getAllGallerist() {

        List<DtoGallerist> dtoGalleristList= new ArrayList<>();

        Sort sort= Sort.by(Sort.Direction.ASC,"id");

        List<Gallerist> allGallerist = galleristRepository.findAll(sort);

        allGallerist.forEach(gallerist -> {

            DtoAddress dtoAddress= new DtoAddress();
            DtoGallerist dtoGallerist= new DtoGallerist();

            BeanUtils.copyProperties(gallerist.getAddress(),dtoAddress);
            BeanUtils.copyProperties(gallerist,dtoGallerist);

            dtoGallerist.setAddress(dtoAddress);

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

        galleristRepository.deleteById(dtoGalleristDeleteReq.getGalleristId());

        return "Success";
    }

}

