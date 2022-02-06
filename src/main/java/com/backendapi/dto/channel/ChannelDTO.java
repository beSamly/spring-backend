package com.backendapi.dto.channel;

import com.backendapi.dto.user.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChannelDTO {

    private Long channelId;

    private String channelName;

    private Boolean isPrivate;

    private List<UserDTO> channelMembers;
}
