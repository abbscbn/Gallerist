package com.abbascoban.gallerist.repository;

import com.abbascoban.gallerist.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {

   Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
