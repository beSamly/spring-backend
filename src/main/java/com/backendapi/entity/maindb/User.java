package com.backendapi.entity.maindb;

import com.backendapi.custom_constraint.Password;
import com.backendapi.service.SecurityHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private @Id @GeneratedValue Long id;

    @Length(max=15)
    private String firstName;

    @Length(max=15)
    private String lastName;

    @Email
    private String email;

    private String password;

    private String profileUrl;
}
