package com.bodyguard.auth.userpass.bodyguard_auth_username_password.controller;


import com.bodyguard.auth.userpass.bodyguard_auth_username_password.dto.UserDTO;
import com.bodyguard.auth.userpass.bodyguard_auth_username_password.entity.UserEntity;
import com.bodyguard.auth.userpass.bodyguard_auth_username_password.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user){
        UserEntity entity = new UserEntity(user.getUsername(), user.getPassword());
        userRepository.save(entity);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/verifyUser")
    public ResponseEntity<String> verifyUser(@RequestBody UserDTO user){
        UserEntity userEntity =  userRepository.findUserByUserName(user.getUsername());
        if(userEntity == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Exists. Please Sign Up");
        }
        if(Objects.equals(userEntity.getUsername(), user.getUsername()) && Objects.equals(userEntity.getPassword(), user.getPassword())){
            return ResponseEntity.ok("Valid user");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username or password");
    }
}
