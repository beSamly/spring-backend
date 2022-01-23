package com.backendapi.entity.maindb;

import com.backendapi.service.SecurityHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(indexes = {
        @Index(name="groupId", columnList = "groupId"),
        @Index(name="channelId", columnList = "channelId"),
        @Index(name="senderId", columnList = "senderId"),
})
public class Message {

    private @Id
    @GeneratedValue
    Long id;

    private Long senderId;

    private Long groupId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="channelId")
    private Channel channel;

    private String content;

    @CreationTimestamp
    private LocalDateTime createdTime;
}
