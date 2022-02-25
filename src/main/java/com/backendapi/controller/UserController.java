package com.backendapi.controller;

import com.backendapi.dto.SuccessResponse;
import com.backendapi.dto.auth.SignInSuccessDTO;
import com.backendapi.dto.user.UserDTO;
import com.backendapi.repository.UserRepository;
import com.backendapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    SuccessResponse<List<UserDTO>> getAllUser() {
        return new SuccessResponse<>(this.userService.getAllUser());
    }
}
