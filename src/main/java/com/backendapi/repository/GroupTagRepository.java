package com.backendapi.repository;

import com.backendapi.entity.maindb.Group;
import com.backendapi.entity.maindb.GroupTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupTagRepository extends JpaRepository<GroupTag, Long> {

}