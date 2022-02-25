package com.backendapi.controller;

import com.backendapi.dto.SuccessResponse;
import com.backendapi.dto.channel.AddUsersToChannelDTO;
import com.backendapi.dto.channel.ChannelDTO;
import com.backendapi.dto.channel.CreateDMChannelDTO;
import com.backendapi.dto.channel.CreateGroupChannelDTO;
import com.backendapi.entity.maindb.Channel;
import com.backendapi.entity.maindb.Group;
import com.backendapi.entity.maindb.User;
import com.backendapi.repository.ChannelRepository;
import com.backendapi.service.service_interface.IChannelService;
import com.backendapi.service.service_interface.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/channel")
public class ChannelController {

    private final ChannelRepository channelRepository;
    private final IUserService userService;
    private final IChannelService channelService;

    public ChannelController(ChannelRepository channelRepository, IUserService userService, IChannelService channelService) {
        this.channelRepository = channelRepository;
        this.channelService = channelService;
        this.userService = userService;
    }

    @GetMapping("/get")
    public SuccessResponse<List<ChannelDTO>> getGroups() {
        List<Channel> channels = this.channelRepository.findAll();
        return new SuccessResponse(channels.stream().map(Channel::toDTO).collect(Collectors.toList()));
    }

    @PostMapping("/create/group-channel")
    public SuccessResponse<ChannelDTO> createChannelInGroup(@RequestBody @Valid CreateGroupChannelDTO createChannelDTO, @RequestAttribute("user") User user, @RequestAttribute("group") Group group) {
        Channel newChannel = new Channel();
        newChannel.setChannelName(createChannelDTO.getChannelName());
        newChannel.setIsPrivate(createChannelDTO.getIsPrivate());
        newChannel.setGroup(group);

        //TODO if channelName already exists in group, then throw error

        this.channelRepository.save(newChannel);

        return new SuccessResponse<ChannelDTO>(newChannel.toDTO());
    }

    @PostMapping("/create/dm-channel")
    public SuccessResponse<ChannelDTO> createDMChannel(@RequestBody @Valid CreateDMChannelDTO dto, @RequestAttribute("user") User user) {
        Channel newChannel = new Channel();
        newChannel.setIsPrivate(true);
        newChannel.setGroup(null);

        List<Long> userIds = dto.getUserIds();
        List<User> channelMembers = new ArrayList<User>();
        userIds.stream().forEach((Long userId) -> {
            Optional<User> userToAdd = this.userService.getUserById(userId);
            if (userToAdd.isPresent()) channelMembers.add(userToAdd.get());
        });

        channelMembers.add(user);
        newChannel.setChannelMembers(channelMembers);

        this.channelRepository.save(newChannel);

        return new SuccessResponse<ChannelDTO>(newChannel.toDTO());
    }

    @PostMapping("/add/users")
    public SuccessResponse<Void> addUsers(@RequestBody @Valid AddUsersToChannelDTO dto, @RequestAttribute("channel") Channel channel) {
        List<User> channelMembers = channel.getChannelMembers();

        List<Long> userIds = dto.getUserIds();
        userIds.stream().forEach((Long userId) -> {
            Optional<User> user = this.userService.getUserById(userId);
            if (user.isPresent()) channelMembers.add(user.get());
        });

        this.channelService.saveChannel(channel);

        return new SuccessResponse<>();
    }
}
