package com.backendapi.webMvcConfig;

import com.backendapi.interceptor.AuthInterceptor;
import com.backendapi.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//    private final RequestInterceptor requestInterceptor;
    private final AuthInterceptor authInterceptor;

    public WebMvcConfig(AuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
//        this.requestInterceptor = requestInterceptor;
    }

    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(this.requestInterceptor);

      this.addAuthInterceptor(registry);
    }

    public void addAuthInterceptor(InterceptorRegistry registry){
        ArrayList<String> list = new ArrayList<String>();
        list.add("/employees");
        list.add("/employees/**");
        list.add("/group/**");
        registry.addInterceptor(this.authInterceptor).addPathPatterns(list);
    }
}