package com.backendapi.exception;

public class UnauthorizedException extends CustomException{
    public UnauthorizedException(String message) {
        super(message);
    }
}
