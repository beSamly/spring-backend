package com.backendapi.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.backendapi.constants.ERROR_MESSAGE;
import com.backendapi.constants.RESPONSE_RESULT_TYPE;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.backendapi.dto.SignUpDTO;
import com.backendapi.repository.AuthRepository;
import org.junit.jupiter.api.Test;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthRepository authRepository;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
//        when(service.greet()).thenReturn("Hello, Mock");

        SignUpDTO signUpRequest = new SignUpDTO("sam", "lee", "invalidemailform", "12Tz!@rg3we");

        String content = new ObjectMapper().writeValueAsString(signUpRequest);

        this.mockMvc
                .perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.result").value(equalTo(RESPONSE_RESULT_TYPE.INVALID_INPUT)))
                .andExpect(jsonPath("$.message").value(equalTo(ERROR_MESSAGE.INVALID_EMAIL)));

 /*       this.mockMvc.perform(post("/auth/signup")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, Mock")));
*/
    }
}
