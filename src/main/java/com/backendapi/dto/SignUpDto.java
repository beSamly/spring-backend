package com.backendapi.dto;

import com.backendapi.custom_constraint.Password;
import com.backendapi.service.SecurityHelper;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;


@Getter
@Setter
public class SignUpDto {

    @Length(max=15)
    private String firstName;

    @Length(max=15)
    private String lastName;

    @Email
    private String email;

    @Password
    private String password;
}
