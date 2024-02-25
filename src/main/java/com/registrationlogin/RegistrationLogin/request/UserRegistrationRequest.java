package com.registrationlogin.RegistrationLogin.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserRegistrationRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
