package com.abbascoban.gallerist.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoGalleristUI extends DtoBase {


    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String galleristName;

    @NotNull
    private String tckn;

    @NotNull
    private Date birthOfDate;

    @NotNull
    private Long addressId;


    private Long userId;

}
