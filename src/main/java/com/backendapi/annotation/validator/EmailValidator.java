package com.backendapi.annotation.validator;

import com.backendapi.annotation.IsEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<IsEmail, String> { // 1

    @Override
    public void initialize(IsEmail emailUnique) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) { // 2
        if (value == null) {
            return false;
        }

        boolean isEmail = value.contains("@");

        return isEmail;
    }
}
