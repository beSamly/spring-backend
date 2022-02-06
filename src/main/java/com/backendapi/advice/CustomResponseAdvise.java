package com.backendapi.advice;

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
