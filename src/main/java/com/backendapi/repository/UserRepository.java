package com.backendapi.repository;

import com.backendapi.entity.maindb.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String emailAddress);
}