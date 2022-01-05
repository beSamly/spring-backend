package com.backendapi.entity.maindb;

import com.backendapi.custom_constraint.Password;
import com.backendapi.service.SecurityHelper;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Getter
@Setter
@Entity
public class User {

    private @Id @GeneratedValue Long id;

    @Length(max=15)
    private String firstName;

    @Length(max=15)
    private String lastName;

    @Email
    private String email;

    private String password;

    public User(Long id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = SecurityHelper.hash(password);
    }
}
