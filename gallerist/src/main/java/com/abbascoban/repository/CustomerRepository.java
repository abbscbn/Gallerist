package com.abbascoban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abbascoban.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
