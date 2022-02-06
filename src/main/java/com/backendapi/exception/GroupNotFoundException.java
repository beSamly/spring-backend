package com.backendapi.exception;

import com.backendapi.constants.RESPONSE_RESULT_TYPE;

public class GroupNotFoundException extends CustomException{

    public GroupNotFoundException(String message) {
        super(message, RESPONSE_RESULT_TYPE.GROUP_INFO_NOT_FOUND);
    }
}
