package com.backendapi.custom_constraint.validator;


import com.backendapi.custom_constraint.IsEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<IsEmail, String> {

    @Override
    public void initialize(IsEmail emailUnique) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        boolean isEmail = value.contains("@");

        return isEmail;
    }
}