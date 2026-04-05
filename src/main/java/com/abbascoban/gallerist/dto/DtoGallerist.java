package com.abbascoban.gallerist.dto;


import com.abbascoban.gallerist.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoGallerist extends BaseEntity {


    private String firstName;


    private String lastName;


    private DtoAddress address;


}
