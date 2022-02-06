package com.backendapi.entity.maindb;

import com.backendapi.dto.channel.ChannelDTO;
import com.backendapi.dto.group.GroupDTO;
import com.backendapi.dto.group.GroupTagDTO;
import com.backendapi.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "\"Group\"")
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    private @Id
    @GeneratedValue
    Long groupId;

    @Length(min = 5, max = 25)
    private String groupName;

    private Boolean isPrivate;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "GroupOwner",
            joinColumns = @JoinColumn(name = "groupId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private List<User> groupOwners = new ArrayList<User>();

    @ManyToMany
    @JoinTable(name = "GroupMember",
            joinColumns = @JoinColumn(name = "groupId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private List<User> groupMembers = new ArrayList<User>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Channel> channelList = new ArrayList<Channel>();

//    @OneToMany(mappedBy = "group", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
//    private List<GroupTag> tags;

    @OneToMany(mappedBy = "group", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
//    @JoinColumn(name = "groupId")
    private List<GroupTag> groupTagList = new ArrayList<GroupTag>();

    public GroupDTO toDTO() {

        List<UserDTO> groupOwnerDTOList = this.groupOwners.stream()
                .map(User::toDTO)
                .collect(Collectors.toList());

        List<ChannelDTO> channelDTOList = this.channelList.stream()
                .map(Channel::toDTO)
                .collect(Collectors.toList());

        List<UserDTO> groupMemberDTOList = this.groupMembers.stream()
                .map(User::toDTO)
                .collect(Collectors.toList());

        List<GroupTagDTO> groupTagDTOList = this.groupTagList.stream()
                .map(GroupTag::toDTO)
                .collect(Collectors.toList());

        return new GroupDTO(this.groupId, this.groupName, this.isPrivate, groupOwnerDTOList, groupMemberDTOList, channelDTOList, groupTagDTOList);
    }

    public void addGroupMember(User user) {
        this.groupMembers.add(user);
    }

    public void addGroupOwner(User user) {
        this.groupOwners.add(user);
    }

    public void addGroupTag(GroupTag tag) {
        this.groupTagList.add(tag);
    }

    public void addChannelList(Channel channel) {
        this.channelList.add(channel);
    }
}
