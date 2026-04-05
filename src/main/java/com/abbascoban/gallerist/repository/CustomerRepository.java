package com.abbascoban.gallerist.repository;

import com.abbascoban.gallerist.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}

