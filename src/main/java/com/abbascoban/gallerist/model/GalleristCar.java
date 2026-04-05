package com.abbascoban.gallerist.model;


import com.abbascoban.gallerist.enums.CarStatusType;
import com.abbascoban.gallerist.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "gallerist_car")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GalleristCar extends BaseEntity{

    @ManyToOne
    private Gallerist gallerist;

    @ManyToOne
    private Car car;

    @Column(name = "plaka")
    private String plaka;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "damage_price")
    private BigDecimal damagePrice;

    @Column(name = "currencyType")
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    @Column(name = "car_status_type")
    @Enumerated(EnumType.STRING)
    private CarStatusType carStatusType;




}
