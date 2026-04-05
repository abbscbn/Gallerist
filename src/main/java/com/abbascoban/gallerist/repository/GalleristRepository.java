package com.abbascoban.gallerist.repository;

import com.abbascoban.gallerist.model.Gallerist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface GalleristRepository extends JpaRepository<Gallerist, Long> {

}

