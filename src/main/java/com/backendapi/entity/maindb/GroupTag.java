package com.backendapi.entity.maindb;

import com.backendapi.entity.maindb.composite_key.GroupTagId;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@IdClass(GroupTagId.class)
public class GroupTag implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="groupdId")
    private Group group;

    @Id
    private String tag;
}


