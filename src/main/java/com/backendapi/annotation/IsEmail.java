package com.backendapi.annotation;

import com.backendapi.annotation.validator.EmailValidator;
import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface IsEmail {
    String message() default "should email";
    Class[] groups() default {};
    Class[] payload() default {};
}
