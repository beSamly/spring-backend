package com.backendapi.service.service_interface;

import com.backendapi.dto.user.UserDTO;
import com.backendapi.entity.maindb.Channel;
import com.backendapi.entity.maindb.User;

import java.util.List;
import java.util.Optional;

public interface IChannelService {
    void saveChannel(Channel channel);
}
