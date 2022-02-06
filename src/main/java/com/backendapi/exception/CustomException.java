package com.backendapi.exception;

import lombok.Getter;

@Getter
public class CustomException extends Exception {

    private String resultType;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, String resultType) {
        super(message);
        this.resultType = resultType;
    }
}
