package com.backendapi.exception;

import com.backendapi.constants.RESPONSE_RESULT_TYPE;

public class UnauthorizedException extends CustomException {
    public UnauthorizedException(String message) {
        super(message, RESPONSE_RESULT_TYPE.UNAUTHENTICATED);
    }
}
