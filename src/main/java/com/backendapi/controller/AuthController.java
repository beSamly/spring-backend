package com.backendapi.controller;

import com.backendapi.dto.SignInDto;
import com.backendapi.dto.SignUpDto;
import com.backendapi.repository.AuthRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthRepository authRepository;

    public AuthController(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @PostMapping("/signin")
    SignInDto signIn(@RequestBody @Valid SignInDto signInDto)
    {
        System.out.println(signInDto);
        return signInDto;
    }

    @PostMapping("/signup")
    SignUpDto signUp(@RequestBody @Valid SignUpDto signUpDto)
    {
        System.out.println(signUpDto);
        return signUpDto;
    }
}
