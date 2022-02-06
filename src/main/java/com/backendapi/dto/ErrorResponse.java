package com.backendapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse extends BaseResponse {
    private String message;

    public ErrorResponse(String result, String message) {
        super(result);
        this.message = message;
    }
}