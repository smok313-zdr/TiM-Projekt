package com.example.shop.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private Set<String> roles;
}
