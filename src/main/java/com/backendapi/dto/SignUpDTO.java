package com.backendapi.dto;

import com.backendapi.custom_constraint.IsEmail;
import com.backendapi.custom_constraint.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;



@Getter
@Setter
@AllArgsConstructor
public class SignUpDTO {

    @Length(max=15)
    private String firstName;

    @Length(max=15)
    private String lastName;

    @IsEmail
    private String email;

    @Password
    private String password;
}
