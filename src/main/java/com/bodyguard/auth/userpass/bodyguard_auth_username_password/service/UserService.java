package com.bodyguard.auth.userpass.bodyguard_auth_username_password.service;

import com.bodyguard.auth.userpass.bodyguard_auth_username_password.dto.UserDTO;
import com.bodyguard.auth.userpass.bodyguard_auth_username_password.entity.ScopeEntity;
import com.bodyguard.auth.userpass.bodyguard_auth_username_password.entity.UserEntity;
import com.bodyguard.auth.userpass.bodyguard_auth_username_password.repository.ScopeRepository;
import com.bodyguard.auth.userpass.bodyguard_auth_username_password.repository.UserRepository;
import com.bodyguard.auth.userpass.bodyguard_auth_username_password.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ScopeRepository scopeRepository;

    public String verifyUser(UserDTO userDTO) {
        final UserEntity userEntity = userRepository.findUserByUserName(userDTO.getUsername());
        if (userEntity != null && Objects.equals(userEntity.getUsername(), userDTO.getUsername()) && Objects.equals(userEntity.getPassword(), userDTO.getPassword())) {
            List<ScopeEntity> scopeEntityList = scopeRepository.findScopes(userEntity.getRoleId());
            final StringBuilder scopes = new StringBuilder();
            scopeEntityList.forEach(scope ->{
                scopes.append(scope.getPermission());
                scopes.append(",");
              }
            );
            return jwtUtil.generateToken(userDTO.getUsername(), scopes.toString());
        }
        return null;
    }
}
