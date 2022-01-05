package com.backendapi.repository;

import com.backendapi.entity.Employee;
import com.backendapi.entity.maindb.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<User, Long> {

}