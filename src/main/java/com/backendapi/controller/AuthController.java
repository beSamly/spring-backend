package com.backendapi.controller;

import com.backendapi.dto.SignInDTO;
import com.backendapi.dto.SignUpDTO;
import com.backendapi.dto.SuccessDTO;
import com.backendapi.repository.AuthRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthRepository authRepository;
    private final ObjectMapper objectMapper;

    public AuthController(AuthRepository authRepository, ObjectMapper objectMapper) {
        this.authRepository = authRepository;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/signin")
    SignInDTO signIn(@RequestBody @Valid SignInDTO signInDto) {
        System.out.println(signInDto);
        return signInDto;
    }

    @PostMapping("/signup")
    SignUpDTO signUp(@RequestBody @Valid SignUpDTO signUpDto) {
        try {

            String content = objectMapper.writeValueAsString(signUpDto);
            System.out.println(content);

        } catch (JsonProcessingException e) {

        }

        return signUpDto;
    }
}
