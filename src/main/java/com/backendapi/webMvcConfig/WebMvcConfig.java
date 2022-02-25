package com.backendapi.webMvcConfig;

import com.backendapi.interceptor.AuthInterceptor;
import com.backendapi.interceptor.ChannelInfoInterceptor;
import com.backendapi.interceptor.GroupInfoInterceptor;
import com.backendapi.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //    private final RequestInterceptor requestInterceptor;
    private final AuthInterceptor authInterceptor;
    private final GroupInfoInterceptor groupInfoInterceptor;
    private final ChannelInfoInterceptor channelInfoInterceptor;

    public WebMvcConfig(AuthInterceptor authInterceptor, GroupInfoInterceptor groupInfoInterceptor, ChannelInfoInterceptor channelInfoInterceptor) {
        this.authInterceptor = authInterceptor;
        this.groupInfoInterceptor = groupInfoInterceptor;
        this.channelInfoInterceptor = channelInfoInterceptor;
//        this.requestInterceptor = requestInterceptor;
    }

    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(this.requestInterceptor);

        this.addAuthInterceptor(registry);
        this.addGroupInfoInterceptor(registry);
        this.addChannelInfoInterceptor(registry);
    }

    public void addAuthInterceptor(InterceptorRegistry registry) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("/**");

        registry.addInterceptor(this.authInterceptor).addPathPatterns(list);
    }

    public void addGroupInfoInterceptor(InterceptorRegistry registry) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("/channel/create");
        registry.addInterceptor(this.groupInfoInterceptor).addPathPatterns(list);
    }

    public void addChannelInfoInterceptor(InterceptorRegistry registry) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("/message/**");
        list.add("/channel/add/users");
        registry.addInterceptor(this.channelInfoInterceptor).addPathPatterns(list);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
    }
}