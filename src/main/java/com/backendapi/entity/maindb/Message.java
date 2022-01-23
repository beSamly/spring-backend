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
        @Index(name="createdTime", columnList = "createdTime")
})
public class Message {

    private @Id
    @GeneratedValue
    Long id;

    @Length(max=15)
    private Long senderId;

    @Length(max=15)
    private String groupId;

    private String content;

    @CreationTimestamp
    private LocalDateTime createdTime;
}
