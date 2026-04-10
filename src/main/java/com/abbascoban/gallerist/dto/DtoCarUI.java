package com.abbascoban.gallerist.dto;

import java.math.BigDecimal;


import com.abbascoban.gallerist.enums.CarStatusType;
import com.abbascoban.gallerist.enums.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoCarUI extends DtoBase {



    private String brand;


    private String model;


    private Integer productionYear;


    private MultipartFile file;



}
