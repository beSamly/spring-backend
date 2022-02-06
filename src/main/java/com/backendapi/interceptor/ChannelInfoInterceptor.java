package com.backendapi.interceptor;

import com.backendapi.entity.maindb.Channel;
import com.backendapi.exception.ChannelNotFoundException;
import com.backendapi.repository.ChannelRepository;
import com.backendapi.repository.ChannelRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

@Component
public class ChannelInfoInterceptor implements HandlerInterceptor {

    private final ChannelRepository channelRepository;

    public ChannelInfoInterceptor(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ChannelNotFoundException {
        String channelId = request.getParameter("channelId");

        if(channelId == null){
            throw new ChannelNotFoundException("Channel Id is not defined on request parameter");
        }

        Optional<Channel> optionalChannel = this.channelRepository.findById(Long.parseLong(channelId));

        if(optionalChannel.isPresent() == false){
            throw new ChannelNotFoundException("Channel Id is not defined on request parameter");
        }

        String method = request.getMethod();
        if (Arrays.asList("POST", "PUT", "DELETE").contains(method)) {
            request.setAttribute("channel", optionalChannel.get());
        }

        return true;
    }
}