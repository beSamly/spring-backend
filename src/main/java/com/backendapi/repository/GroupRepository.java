package com.backendapi.repository;

import com.backendapi.entity.maindb.Group;
import com.backendapi.entity.maindb.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {

}