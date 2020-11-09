package com.zerod.authdemo.services;

import com.zerod.authdemo.exceptions.RefreshTokenException;
import com.zerod.authdemo.models.RefreshToken;
import com.zerod.authdemo.repositories.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

    private RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken(){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken validateRefreshToken(String token) throws RefreshTokenException {
        return refreshTokenRepository.findByToken(token).orElseThrow(() -> new RefreshTokenException("Refresh Token not found"));
    }

    public void deleteRefreshToken(String token){
        refreshTokenRepository.deleteByToken(token);
    }
}
