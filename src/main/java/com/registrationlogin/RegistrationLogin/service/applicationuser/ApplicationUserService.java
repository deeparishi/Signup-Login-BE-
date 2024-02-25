package com.registrationlogin.RegistrationLogin.service.applicationuser;

import com.registrationlogin.RegistrationLogin.entity.applicationuser.ApplicationUser;
import com.registrationlogin.RegistrationLogin.entity.token.Token;
import com.registrationlogin.RegistrationLogin.repository.ApplicationUserRepository;
import com.registrationlogin.RegistrationLogin.repository.token.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ApplicationUserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "User with this email not found";

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    TokenRepository tokenRepository;
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        return applicationUserRepository.findByEmail(email)
                .orElseThrow( () ->
                        new UsernameNotFoundException (String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUp(ApplicationUser applicationUser){
       Boolean userExisit = applicationUserRepository.findByEmail(applicationUser.getEmail()).isPresent();

       if(userExisit){
          return "User Already exsist";
       }
        String encodePassword = passwordEncoder().encode(applicationUser.getPassword());
//       bCryptPasswordEncoder.encode(applicationUser.getPassword());
       applicationUser.setPassword(encodePassword);
       applicationUserRepository.save(applicationUser);

        String token =UUID.randomUUID().toString();
        Token newToken = new Token(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                applicationUser
        );
        tokenRepository.save(newToken);
        return token;
    }
    public void enableAppUser(String email) {
         applicationUserRepository.enableAppUser(email);
    }
}
