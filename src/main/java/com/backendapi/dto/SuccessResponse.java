package com.backendapi.dto;

import com.backendapi.constants.RESPONSE_RESULT_TYPE;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponse<T> extends BaseResponse {

    private T data;

    public SuccessResponse() {
        super(RESPONSE_RESULT_TYPE.SUCCESS);
    }

    public SuccessResponse(T t) {
        super(RESPONSE_RESULT_TYPE.SUCCESS);
        this.data = t;
    }
}
