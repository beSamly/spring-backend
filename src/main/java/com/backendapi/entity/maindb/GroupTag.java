package com.backendapi.entity.maindb;

import com.backendapi.dto.group.GroupTagDTO;
import com.backendapi.entity.maindb.composite_key.GroupTagId;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
public class GroupTag {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="groupdId")
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "groupId")
//    @JsonIdentityReference(alwaysAsId = true)
    private Group group;

//    @Id
//    private Long groupId;

    @Id
    private String tag;

    public GroupTagDTO toDTO() {
        return new GroupTagDTO(this.group.getGroupId(), this.tag);
    }
}


