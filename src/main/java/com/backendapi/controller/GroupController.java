package com.backendapi.controller;

import com.backendapi.dto.SuccessResponse;
import com.backendapi.dto.group.CreateGroupDTO;
//import com.backendapi.entity.maindb.Group;
import com.backendapi.dto.group.GroupDTO;
import com.backendapi.entity.maindb.Group;
import com.backendapi.entity.maindb.GroupTag;
import com.backendapi.entity.maindb.User;
import com.backendapi.repository.GroupRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/group")
public class GroupController {

    private final GroupRepository groupRepository;

    public GroupController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @GetMapping("/get")
    public List<GroupDTO> getGroups() {
        List<Group> groups = this.groupRepository.findAll();
        return groups.stream().map(Group::toDTO).collect(Collectors.toList());
    }

    @PostMapping("/create")
    public SuccessResponse<GroupDTO> createGroup(@RequestBody @Valid CreateGroupDTO createGroupDTO, @RequestAttribute("user") User user) {
        Group newGroup = new Group();
        newGroup.setGroupName(createGroupDTO.getGroupName());
        newGroup.setIsPrivate(createGroupDTO.getIsPrivate());

        createGroupDTO.getTags().stream().forEach(tag -> {
            GroupTag groupTag = new GroupTag(newGroup, tag);
            newGroup.addGroupTag(groupTag);
        });

        newGroup.addGroupMember(user);
        newGroup.addGroupOwner(user);

        Group createdGroup = this.groupRepository.save(newGroup);

        return new SuccessResponse<GroupDTO>(createdGroup.toDTO());
    }
}
