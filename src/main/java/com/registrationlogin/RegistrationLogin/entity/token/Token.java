package com.registrationlogin.RegistrationLogin.entity.token;

import com.registrationlogin.RegistrationLogin.entity.applicationuser.ApplicationUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity(name = "token_generator")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private ApplicationUser applicationUser;

    public Token(String token, LocalDateTime createdAt, LocalDateTime expiresAt, ApplicationUser applicationUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.applicationUser = applicationUser;
    }
}
