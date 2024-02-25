package com.registrationlogin.RegistrationLogin.repository.token;

import com.registrationlogin.RegistrationLogin.entity.token.Token;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
@Repository
@Transactional
public interface TokenRepository extends JpaRepository<Token,Long> {

     Optional<Token> findByToken(String token) ;

     @Query("SELECT t.token FROM token_generator t WHERE t.token = :tokenValue")
     String findTokenByValue(@Param("tokenValue") String tokenValue);



     @org.springframework.transaction.annotation.Transactional
     @Modifying
     @Query(value = "UPDATE token_generator c " +
             "SET c.confirmed_at = ?2 " +
             "WHERE c.token = ?1", nativeQuery = true)
     void updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
