package com.ssd.user_service.controller;

import com.ssd.user_service.dto.UserDTO;
import com.ssd.user_service.dto.UserRegistrationRequest;
import com.ssd.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private int count = 1;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserRegistrationRequest request) throws UnknownHostException {
        System.out.printf("Call: %d - %s \n", count++, LocalDateTime.now());
        UserDTO createdUser = userService.register(request);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) throws UnknownHostException {
        System.out.printf("Call: %d - %s \n", count++, LocalDateTime.now());
        UserDTO user = userService.getUser(username);
        return ResponseEntity.ok(user);
    }
}
