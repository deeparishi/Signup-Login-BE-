package com.registrationlogin.RegistrationLogin.service.email;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class EmailValidator implements Predicate<String> {

    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Override
    public boolean test(String email) {
            Matcher matcher = EMAIL_PATTERN.matcher(email);
            return matcher.matches();
        }
    }

