package com.backendapi.dto;

import com.backendapi.annotation.IsEmail;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class SignInDto {

    @IsEmail
    private String email;

    @Min(message = "금액은 5000원이상이여야 합니다", value = 5000)
    @NotBlank
    @NotNull
    private String password;

}
