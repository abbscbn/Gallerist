package com.abbascoban.gallerist.repository;

import com.abbascoban.gallerist.model.Gallerist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface GalleristRepository extends JpaRepository<Gallerist, Long> {


    Optional<Gallerist> findByUser_Username(String username);
}

