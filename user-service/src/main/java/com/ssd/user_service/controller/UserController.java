package com.ssd.user_service.controller;

import com.ssd.user_service.dto.UserDTO;
import com.ssd.user_service.dto.UserRegistrationRequest;
import com.ssd.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserRegistrationRequest request) {
        UserDTO createdUser = userService.register(request);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        UserDTO user = userService.getUser(username);
        return ResponseEntity.ok(user);
    }
}
