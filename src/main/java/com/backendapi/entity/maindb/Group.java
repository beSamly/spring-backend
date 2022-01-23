package com.backendapi.entity.maindb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;

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
    private List<User> groupOwners;

    @OneToMany(mappedBy = "group", cascade = CascadeType.PERSIST)
    private List<GroupTag> tags;
}
