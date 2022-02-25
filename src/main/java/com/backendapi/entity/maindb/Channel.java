package com.backendapi.entity.maindb;

import com.backendapi.dto.channel.ChannelDTO;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Channel {

    private @Id
    @GeneratedValue
    Long channelId;

    @Length(min = 5, max = 25)
    private String channelName;

    private Boolean isPrivate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupdId")
    private Group group = new Group();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ChannelMember",
            joinColumns = @JoinColumn(name = "channelId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private List<User> channelMembers = new ArrayList<User>();

    @OneToMany(mappedBy = "channel", fetch = FetchType.LAZY)
    private List<Message> messages = new ArrayList<Message>();


    public ChannelDTO toDTO() {
        return new ChannelDTO(this.channelId, this.channelName, this.isPrivate, this.channelMembers.stream().map(User::toDTO).collect(Collectors.toList()));
    }

    public List<Integer> getChannelMemberIds() {
        return this.getChannelMembers().stream().map(User -> User.getId().intValue()).collect(Collectors.toList());
    }
}
