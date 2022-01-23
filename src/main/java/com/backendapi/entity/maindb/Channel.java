package com.backendapi.entity.maindb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Channel {

    private @Id
    @GeneratedValue
    Long channelId;

    @Length(min = 5, max = 15)
    private String channelName;

    private Boolean isPrivate;

    @ManyToMany
    @JoinTable(name = "ChannelMember",
            joinColumns = @JoinColumn(name = "channelId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private List<User> channelMembers;

    @OneToMany(mappedBy = "channel", fetch = FetchType.LAZY)
    private List<Message> messages;
}
