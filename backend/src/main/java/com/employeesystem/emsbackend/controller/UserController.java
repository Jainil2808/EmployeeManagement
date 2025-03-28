package com.employeesystem.emsbackend.controller;

import com.employeesystem.emsbackend.dto.LoginResponseDto;
import com.employeesystem.emsbackend.dto.UserLoginDto;
import com.employeesystem.emsbackend.dto.UserRegistrationDto;
import com.employeesystem.emsbackend.entity.User;
import com.employeesystem.emsbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        User registeredUser = userService.registerUser(registrationDto);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody UserLoginDto loginDto) {
        LoginResponseDto loginResponse = userService.loginUser(loginDto);
        return ResponseEntity.ok(loginResponse);
    }
}