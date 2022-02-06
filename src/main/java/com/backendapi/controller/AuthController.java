package com.backendapi.controller;

import com.backendapi.dto.SignInDTO;
import com.backendapi.dto.SignUpDTO;
import com.backendapi.dto.SuccessDTO;
import com.backendapi.dto.user.UserDTO;
import com.backendapi.entity.maindb.User;
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
    UserDTO signUp(@RequestBody @Valid SignUpDTO signUpDto) {

        User newUser = new User();
        newUser.setEmail(signUpDto.getEmail());
        newUser.setFirstName(signUpDto.getFirstName());
        newUser.setPassword(signUpDto.getLastName());
        newUser.setProfileUrl(null);
        authRepository.save(newUser);

        return newUser.toDTO();
    }
}
