package com.abbascoban.gallerist.repository;

import com.abbascoban.gallerist.model.SaledCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SaledCarRepository extends JpaRepository<SaledCar, Long> {

    List<SaledCar> findByGalleristCar_Gallerist_Id(Long galleristId);



}
