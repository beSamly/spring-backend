package com.backendapi.controller;

import com.backendapi.dto.json.SignInDto;
import com.backendapi.entity.Employee;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/signin")
    SignInDto signIn(@RequestBody @Valid SignInDto signInDto)
    {
        System.out.println(signInDto);
        return signInDto;
    }
}
