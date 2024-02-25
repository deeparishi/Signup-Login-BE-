package com.registrationlogin.RegistrationLogin.repository;

import com.registrationlogin.RegistrationLogin.entity.applicationuser.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByEmail(String email);
    @Transactional
    @Modifying
    @Query(value = "UPDATE application_user a " +
            "SET a.enabled_or_not = TRUE " +
            "WHERE a.email = ?1", nativeQuery = true)
    void enableAppUser(String email);

//    @Query(value = "SELECT id from from application_user where email = :email")
//    Integer getId(String email);

}
