package com.ssd.user_service.service;

import com.ssd.user_service.dto.UserDTO;
import com.ssd.user_service.dto.UserRegistrationRequest;
import com.ssd.user_service.model.User;
import com.ssd.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDTO register(UserRegistrationRequest request) {
        boolean usernameExists = userRepository.findByUsername(request.getUsername()).isPresent();
        boolean emailExists = userRepository.findByEmail(request.getEmail()).isPresent();

        if (usernameExists || emailExists) {
            throw new IllegalArgumentException("Username or email already exists.");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword()) // should be encrypted!
                .createdAt(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(user);
        return mapToDTO(savedUser);
    }

    public UserDTO getUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User with username '" + username + "' not found."));

        return mapToDTO(user);
    }

    private UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
