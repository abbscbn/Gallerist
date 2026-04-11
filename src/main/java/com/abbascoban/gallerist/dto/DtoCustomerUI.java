package com.abbascoban.gallerist.dto;

import java.util.Date;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class DtoCustomerUI extends DtoBase {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String tckn;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthOfDate;

    private MultipartFile file;

    @NotNull
    private Long addressId;

    @NotNull
    private Long accountId;

    private Long userId;

}
