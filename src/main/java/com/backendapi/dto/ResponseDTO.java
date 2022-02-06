package com.backendapi.dto;

import com.backendapi.constants.RESPONSE_RESULT_TYPE;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO<T> extends BaseResponse {

    private T data;

    public ResponseDTO(T t) {
        super(RESPONSE_RESULT_TYPE.SUCCESS);
        this.data = t;
    }
}
