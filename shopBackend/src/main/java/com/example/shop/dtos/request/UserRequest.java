package com.example.shop.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String surname;
    private String name;
    private String email;
    private String pesel;
    private String fatherName;
}
