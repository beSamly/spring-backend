package com.backendapi.interceptor;

import com.backendapi.exception.UnauthorizedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws UnauthorizedException {
        if(request.getQueryString()==null){
            throw new UnauthorizedException("unauthorized");
        }
        return true;
    }

    //PostHandler : do logging or whatever you want to do
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                              @Nullable ModelAndView modelAndView) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        if (request.getClass().getName().contains("SecurityContextHolderAwareRequestWrapper")) return;
        final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
        final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;
        if (cachingRequest.getContentType() != null && cachingRequest.getContentType().contains("application/json")) {
            if (cachingRequest.getContentAsByteArray() != null && cachingRequest.getContentAsByteArray().length != 0) {
                System.out.println("Request Body : " + objectMapper.readTree(cachingRequest.getContentAsByteArray()));
            }
        }

        if (cachingResponse.getContentType() != null && cachingResponse.getContentType().contains("application/json")) {
            if (cachingResponse.getContentAsByteArray() != null && cachingResponse.getContentAsByteArray().length != 0) {
                System.out.println("Response Body : " + objectMapper.readTree(cachingResponse.getContentAsByteArray()));
            }
        }
    }

//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }

}
