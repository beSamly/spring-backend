package com.backendapi.service;

import com.backendapi.dto.user.UserDTO;
import com.backendapi.entity.maindb.User;
import com.backendapi.repository.UserRepository;
import com.backendapi.service.service_interface.IUserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserService implements IUserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUser() {
        return this.userRepository.findAll().stream().map(User::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return this.userRepository.findById(id);
    }
}
