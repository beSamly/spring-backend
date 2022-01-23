package com.backendapi.entity.maindb.composite_key;

import com.backendapi.entity.maindb.Group;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class GroupTagId implements Serializable {

    private Group group;

    private String tag;
}

