package com.bodyguard.auth.userpass.bodyguard_auth_username_password.controller;

import com.bodyguard.auth.userpass.bodyguard_auth_username_password.dto.UserDTO;
import com.bodyguard.auth.userpass.bodyguard_auth_username_password.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/verifyUser")
    public ResponseEntity<String> verifyUser(@RequestBody UserDTO user){
        String jwtToken =  userService.verifyUser(user);
        if(jwtToken == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid username or password, Or user not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(jwtToken);
    }
}