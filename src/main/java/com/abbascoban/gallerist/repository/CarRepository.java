package com.abbascoban.gallerist.repository;


import com.abbascoban.gallerist.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface CarRepository extends JpaRepository<Car, Long> {


}

