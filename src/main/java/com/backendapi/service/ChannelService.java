package com.backendapi.service;

import com.backendapi.dto.user.UserDTO;
import com.backendapi.entity.maindb.Channel;
import com.backendapi.entity.maindb.User;
import com.backendapi.repository.ChannelRepository;
import com.backendapi.repository.UserRepository;
import com.backendapi.service.service_interface.IChannelService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ChannelService implements IChannelService {
    private final ChannelRepository channelRepository;

    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @Override
    public void saveChannel(Channel channel) {
        this.channelRepository.save(channel);
    }
}
