package com.backendapi.controller;

import com.backendapi.dto.group.CreateGroupDTO;
//import com.backendapi.entity.maindb.Group;
import com.backendapi.entity.maindb.Group;
import com.backendapi.entity.maindb.GroupTag;
import com.backendapi.entity.maindb.User;
import com.backendapi.repository.GroupRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/group")
public class GroupController {

    private final GroupRepository groupRepository;

    public GroupController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @PostMapping("/create")
    Group createGroup(@RequestBody @Valid CreateGroupDTO createGroupDTO, @RequestAttribute("user") User user) {
        Group newGroup = new Group();
        newGroup.setGroupName(createGroupDTO.getGroupName());
        newGroup.setIsPrivate(createGroupDTO.getIsPrivate());

        ArrayList<GroupTag> groupTags = new ArrayList<GroupTag>();
        createGroupDTO.getTags().stream().forEach(tag -> {
            GroupTag groupTag = new GroupTag(newGroup, tag);
            groupTags.add(groupTag);
        });

        newGroup.setTags(groupTags);
        newGroup.setGroupOwners(new ArrayList<User>() {
            {
                add(user);
            }
        });

        Group creatdGroup = this.groupRepository.save(newGroup);

        return creatdGroup;
    }

}
