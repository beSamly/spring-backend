package com.backendapi.controller;

import com.backendapi.dto.SignInDTO;
import com.backendapi.dto.SignUpDTO;
import com.backendapi.dto.auth.SignInSuccessDTO;
import com.backendapi.dto.jwt_data.JWTData;
import com.backendapi.dto.user.UserDTO;
import com.backendapi.entity.maindb.User;
import com.backendapi.helper.JWTHelper;
import com.backendapi.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository authRepository;
    private final ObjectMapper objectMapper;

    public AuthController(UserRepository authRepository, ObjectMapper objectMapper) {
        this.authRepository = authRepository;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/signin")
    SignInSuccessDTO signIn(@RequestBody @Valid SignInDTO signInDto) {
        System.out.println(signInDto);

        User user = authRepository.findByEmail(signInDto.getEmail());
        if (user == null) {
            throw new Error("");
        }

        JWTData jwtData = new JWTData(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
        String token = JWTHelper.createToken(jwtData);

        return new SignInSuccessDTO(token);
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
