package com.abbascoban.gallerist.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSaledCarUI {

    @NotNull
    private Long customerId;
    @NotNull
    private Long galleristCarId;

}
