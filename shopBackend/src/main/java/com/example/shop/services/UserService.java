package com.example.shop.services;

import com.example.shop.dtos.request.AddUserRequest;
import com.example.shop.dtos.request.CreateAccountRequest;
import com.example.shop.dtos.request.LoginRequest;
import com.example.shop.dtos.response.JwtResponse;
import com.example.shop.dtos.response.MessageResponse;
import com.example.shop.dtos.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAll();

    MessageResponse addNew(AddUserRequest userRequest);

    void removeById(String user_id);

    List<UserResponse> getWithFilters(String name);

    UserResponse get(String user_id);

    JwtResponse signIn(LoginRequest loginRequest);

    void addMoney(String userID, int money);

    MessageResponse createAccount(CreateAccountRequest createAccountRequest);
}
