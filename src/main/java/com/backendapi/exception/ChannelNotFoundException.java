package com.backendapi.exception;

import com.backendapi.constants.RESPONSE_RESULT_TYPE;

public class ChannelNotFoundException extends CustomException{

    public ChannelNotFoundException(String message) {
        super(message, RESPONSE_RESULT_TYPE.CHANNEL_INFO_NOT_FOUND);
    }
}
