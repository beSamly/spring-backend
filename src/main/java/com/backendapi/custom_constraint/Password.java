package com.backendapi.custom_constraint;

import com.backendapi.custom_constraint.validator.PasswordValidator;
import com.backendapi.error_message.ERROR_MESSAGE;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
    String message() default ERROR_MESSAGE.WEAK_PASSWORD;
    Class[] groups() default {};
    Class[] payload() default {};

}
