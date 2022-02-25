package com.backendapi.entity.maindb;

import com.backendapi.dto.message.MessageDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "senderId", columnList = "senderId"),
})
public class Message {

    private @Id
    @GeneratedValue
    Long id;

    private Long senderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "channelId")
    private Channel channel;

    private String content;

    @CreationTimestamp
    private LocalDateTime createdTime;

    public MessageDTO toDTO() {
        return new MessageDTO(this.id, this.senderId, this.channel.getChannelId(), this.content, this.createdTime);
    }
}
