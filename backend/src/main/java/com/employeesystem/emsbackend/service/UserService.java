package com.employeesystem.emsbackend.service;

import com.employeesystem.emsbackend.dto.LoginResponseDto;
import com.employeesystem.emsbackend.dto.UserLoginDto;
import com.employeesystem.emsbackend.dto.UserRegistrationDto;
import com.employeesystem.emsbackend.entity.User;
import com.employeesystem.emsbackend.exception.ResourceAlreadyExistsException;
import com.employeesystem.emsbackend.exception.ResourceNotFoundException;
import com.employeesystem.emsbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User registerUser(UserRegistrationDto registrationDto) {
        // Check if username or email already exists
        if (userRepository.existsByUsername(registrationDto.getUsername())) {
            throw new ResourceAlreadyExistsException("Username already exists");
        }

        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already exists");
        }

        // Create new user
        User user = new User();
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setOrganizationName(registrationDto.getOrganizationName());
        user.setUsername(registrationDto.getUsername());

        // In a real-world scenario, you would hash the password
        user.setPassword(registrationDto.getPassword());

        return userRepository.save(user);
    }

    public LoginResponseDto loginUser(UserLoginDto loginDto) {
        // Find user by username
        User user = userRepository.findByUsername(loginDto.getUsername());

        // Check if user exists and password matches
        if (user == null || !user.getPassword().equals(loginDto.getPassword())) {
            throw new ResourceNotFoundException("Invalid username or password");
        }

        // Create login response
        LoginResponseDto responseDto = new LoginResponseDto();
        responseDto.setUserId(user.getId());
        responseDto.setUsername(user.getUsername());
        responseDto.setEmail(user.getEmail());
        responseDto.setOrganizationName(user.getOrganizationName());

        return responseDto;
    }
}