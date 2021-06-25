package com.example.shop.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountRequest {
    @Size(min = 3, max = 20)
    private String username;
    @Size(max = 50)
    @Email
    private String email;
    @Size(min = 6, max = 40)
    private String password;
    private String surname;
    private String name;

}
