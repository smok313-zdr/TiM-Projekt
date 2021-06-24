package com.example.shop.controllers;

import com.example.shop.dtos.request.AddUserRequest;
import com.example.shop.dtos.request.CreateAccountRequest;
import com.example.shop.dtos.request.LoginRequest;
import com.example.shop.dtos.response.JwtResponse;
import com.example.shop.dtos.response.MessageResponse;
import com.example.shop.dtos.response.UserResponse;
import com.example.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/user")
public class UserController {

    private final UserService userService;



    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("sign_in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = userService.signIn(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }


//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("add_user")
    public ResponseEntity addNew(@RequestBody AddUserRequest userRequest) {
        return ResponseEntity.ok(userService.addNew(userRequest));
    }


    @PostMapping("create_account")
    public ResponseEntity<MessageResponse> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        return ResponseEntity.ok(userService.createAccount(createAccountRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("all")
    public ResponseEntity<List<UserResponse>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("with_filters")
    public ResponseEntity<List<UserResponse>> getAllWithFilters(@RequestParam String name ) {
        return new ResponseEntity(userService.getWithFilters(name), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable String id){
        userService.removeById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("{id}")
    public ResponseEntity<UserResponse> get(@PathVariable String id) {
        return new ResponseEntity<>(userService.get(id), HttpStatus.OK);
    }

}