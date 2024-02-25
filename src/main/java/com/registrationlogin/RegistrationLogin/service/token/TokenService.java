package com.registrationlogin.RegistrationLogin.service.token;

import com.registrationlogin.RegistrationLogin.entity.token.Token;
import com.registrationlogin.RegistrationLogin.repository.token.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TokenService {

    @Autowired
    TokenRepository tokenRepository;

    public void saveToken(Token token) {
        tokenRepository.save(token);
    }

    public Optional<Token> getToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public void setConfirmedAt(String token) {
         tokenRepository.updateConfirmedAt(token, LocalDateTime.now());


    }
}
