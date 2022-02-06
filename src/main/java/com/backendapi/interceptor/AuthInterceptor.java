package com.backendapi.interceptor;

import com.backendapi.entity.maindb.User;
import com.backendapi.exception.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws UnauthorizedException {
//        if(request.getQueryString()==null){
//            throw new UnauthorizedException("unauthorized");
//        }

        String method = request.getMethod();
        if (Arrays.asList("POST", "PUT", "DELETE").contains(method)) {
            User user = new User();
            user.setEmail("fromAuthInterceptor@gmail.com");
            user.setLastName("Lee");
            user.setFirstName("Admin");
            user.setId(Long.parseLong("99999"));
            request.setAttribute("user", user);
        }

        return true;
    }
}