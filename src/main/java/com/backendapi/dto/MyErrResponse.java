package com.backendapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyErrResponse {
    private String message;
    private int status;

    public MyErrResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
