package com.backendapi.entity.maindb;

import com.backendapi.dto.message.MessageDTO;
import com.backendapi.service.SecurityHelper;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
