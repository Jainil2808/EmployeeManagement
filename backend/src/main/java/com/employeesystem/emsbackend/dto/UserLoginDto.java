package com.employeesystem.emsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Data Transfer Object for User Login
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDto {
    private String username;
    private String password;
}