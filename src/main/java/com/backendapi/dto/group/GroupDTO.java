package com.backendapi.dto.group;

import com.backendapi.dto.channel.ChannelDTO;
import com.backendapi.dto.user.UserDTO;
import com.backendapi.entity.maindb.GroupTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {
    private Long groupId;

    private String groupName;

    private Boolean isPrivate;

    private List<UserDTO> groupOwners;

    private List<UserDTO> groupMembers;

    private List<ChannelDTO> channelList;

    private List<GroupTagDTO> groupTagList;
}
