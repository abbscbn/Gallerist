package com.abbascoban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abbascoban.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	

}
