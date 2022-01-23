package com.backendapi.advice;

import com.backendapi.annotation.IgnoreResponseBinding;
import com.backendapi.dto.ErrorResponse;
import com.backendapi.dto.SuccessResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/*
@ControllerAdvice
public class CustomResponseAdvise implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object bodyObject, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (methodParameter.getContainingClass().isAnnotationPresent(RestController.class)) {

            if (this.isMethodFromRestController(methodParameter)) {
                if ((!(bodyObject instanceof ErrorResponse)) && (!(bodyObject instanceof SuccessResponse))) {
                    SuccessResponse<Object> responseBody = new SuccessResponse<>(bodyObject);
                    return responseBody;
                }
            }
        }
        return bodyObject;
    }

    private boolean isMethodFromRestController(MethodParameter methodParameter){
        return methodParameter.getContainingClass().isAnnotationPresent(RestController.class);
    }
}*/
