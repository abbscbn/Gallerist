package com.abbascoban.gallerist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "saled_car",uniqueConstraints = {@UniqueConstraint(columnNames = {"galleristCar_id","customer_id"},name = "uq_gallerist_car_customer")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaledCar extends BaseEntity{

    @ManyToOne
    private GalleristCar galleristCar;

    @ManyToOne
    private Customer customer;

}
