package com.backendapi.webMvcConfig;

import com.backendapi.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    AuthInterceptor interceptor;

    public WebMvcConfig(AuthInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    public void addInterceptors(InterceptorRegistry registry) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("/employees");
        list.add("/employees/**");
        registry.addInterceptor(this.interceptor).addPathPatterns(list);
    }
}