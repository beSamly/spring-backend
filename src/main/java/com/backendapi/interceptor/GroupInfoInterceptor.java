package com.backendapi.interceptor;

import com.backendapi.entity.maindb.Group;
import com.backendapi.exception.GroupNotFoundException;
import com.backendapi.repository.GroupRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

@Component
public class GroupInfoInterceptor implements HandlerInterceptor {

    private final GroupRepository groupRepository;

    public GroupInfoInterceptor(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws GroupNotFoundException {
        String groupId = request.getParameter("groupId");

        if(groupId == null){
            throw new GroupNotFoundException("Group Id is not defined on request parameter");
        }

        Optional<Group> optionalGroup = this.groupRepository.findById(Long.parseLong(groupId));

        if(optionalGroup.isPresent() == false){
            throw new GroupNotFoundException("Group Id is not defined on request parameter");
        }

        String method = request.getMethod();
        if (Arrays.asList("POST", "PUT", "DELETE").contains(method)) {
            request.setAttribute("group", optionalGroup.get());
        }

        return true;
    }
}