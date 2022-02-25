package com.backendapi.custom_annotation;

import com.backendapi.constants.ERROR_MESSAGE;
import com.backendapi.custom_constraint.validator.EmailValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreProperty {
}