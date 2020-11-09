package com.zerod.authdemo.repositories;

import com.zerod.authdemo.models.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Refresh token repository
 */
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    /**
     * Find refresh token model by token
     * @param token the refresh token come in
     * @return the refresh token model
     */
    Optional<RefreshToken> findByToken(String token);
    /**
     * Delete refresh token model by token
     * @param token the refresh token come in
     */
    void deleteByToken(String token);
}
