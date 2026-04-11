package com.abbascoban.gallerist.repository;

import com.abbascoban.gallerist.enums.CarStatusType;
import com.abbascoban.gallerist.model.GalleristCar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GalleristCarRepository extends JpaRepository<GalleristCar, Long> {


    List<GalleristCar> findByGallerist_Id(Long galleristId);


    @Query("""
    SELECT gc FROM GalleristCar gc
    WHERE gc.carStatusType = :status
    AND (:brand IS NULL OR gc.car.brand = :brand)
    AND (:model IS NULL OR gc.car.model = :model)
""")
    Page<GalleristCar> filterCars(
            @Param("brand") String brand,
            @Param("model") String model,
            @Param("status") CarStatusType status,
            Pageable pageable
    );

    Page<GalleristCar> findByCarStatusType(CarStatusType carStatusType, Pageable pageable);
}

