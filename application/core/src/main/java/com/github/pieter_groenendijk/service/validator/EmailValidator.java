package com.github.pieter_groenendijk.service.validator;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EmailValidator {
    private static final String EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,7}$";

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}