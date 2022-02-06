package com.backendapi.custom_constraint;


import com.backendapi.custom_constraint.validator.EmailValidator;
import com.backendapi.constants.ERROR_MESSAGE;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface IsEmail {
    String message() default ERROR_MESSAGE.INVALID_EMAIL;
    Class[] groups() default {};
    Class[] payload() default {};
}