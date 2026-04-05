package com.abbascoban.gallerist.repository;

import com.abbascoban.gallerist.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


    Optional<Account> findByaccountNo(String accountNo);



}

