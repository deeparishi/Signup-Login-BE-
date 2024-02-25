package com.registrationlogin.RegistrationLogin.controller;

import com.registrationlogin.RegistrationLogin.request.UserRegistrationRequest;

import com.registrationlogin.RegistrationLogin.service.userregistration.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/userRegistration")
public class UserRegistration {
    @Autowired
    UserRegistrationService userRegistrationService;
    @PostMapping("/register")
    public String register(@RequestBody UserRegistrationRequest userRegistrationRequest){
        return userRegistrationService.register(userRegistrationRequest);
    }

    @GetMapping(path = "/confirmToken")
    public String confirmToken(@RequestParam("token") String token){
        return userRegistrationService.confirmToken(token);
    }
}
