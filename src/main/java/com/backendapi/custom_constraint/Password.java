package com.backendapi.custom_constraint;

import com.backendapi.annotation.validator.EmailValidator;
import com.backendapi.custom_constraint.validator.PasswordValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
    String message() default "Password should contain blah blah";
    Class[] groups() default {};
    Class[] payload() default {};
}
