package com.backendapi.controller;

import com.backendapi.dto.ResponseDTO;
import com.backendapi.dto.channel.ChannelDTO;
import com.backendapi.dto.channel.CreateChannelDTO;
import com.backendapi.dto.group.CreateGroupDTO;
import com.backendapi.dto.group.GroupDTO;
import com.backendapi.entity.maindb.Channel;
import com.backendapi.entity.maindb.Group;
import com.backendapi.entity.maindb.GroupTag;
import com.backendapi.entity.maindb.User;
import com.backendapi.repository.ChannelRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/channel")
public class ChannelController {

    private final ChannelRepository channelRepository;

    public ChannelController(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @GetMapping("/get")
    public ResponseDTO<List<ChannelDTO>> getGroups() {
        List<Channel> channels = this.channelRepository.findAll();
        return new ResponseDTO(channels.stream().map(Channel::toDTO).collect(Collectors.toList()));
    }

    @PostMapping("/create")
    public ResponseDTO<ChannelDTO> createChannel(@RequestBody @Valid CreateChannelDTO createChannelDTO, @RequestAttribute("user") User user, @RequestAttribute("group") Group group) {
        Channel newChannel = new Channel();
        newChannel.setChannelName(createChannelDTO.getChannelName());
        newChannel.setIsPrivate(createChannelDTO.getIsPrivate());
        newChannel.setGroup(group);

        //TODO if channelName already exists in group, then throw error

        this.channelRepository.save(newChannel);

        return new ResponseDTO<ChannelDTO>(newChannel.toDTO());
    }
}
