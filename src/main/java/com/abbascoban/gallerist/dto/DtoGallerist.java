package com.abbascoban.gallerist.dto;


import com.abbascoban.gallerist.model.BaseEntity;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DtoGallerist extends BaseEntity {


    private String firstName;


    private String lastName;


    private String galleristName;


    private String tckn;


    private Date birthOfDate;


    private DtoAddress address;

    private DtoUser user;


}
