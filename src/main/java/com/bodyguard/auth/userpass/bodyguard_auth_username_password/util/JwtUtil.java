package com.bodyguard.auth.userpass.bodyguard_auth_username_password.util;

import com.bodyguard.auth.userpass.bodyguard_auth_username_password.dto.UserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "1111";

    public static String generateToken(UserDTO userDTO, String scopes) {
        return Jwts.builder()
                .setSubject(userDTO.getUsername())
                .claim("scopes", scopes)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000*10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}