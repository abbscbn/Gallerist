package com.abbascoban.gallerist.repository;

import com.abbascoban.gallerist.enums.Role;
import com.abbascoban.gallerist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


   Optional<User> findByUsername(String username);

   boolean existsByRole(Role role);
}
