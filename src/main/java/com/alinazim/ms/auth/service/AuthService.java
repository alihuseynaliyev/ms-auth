package com.alinazim.ms.auth.service;

import com.alinazim.ms.auth.dao.entity.UserEntity;
import com.alinazim.ms.auth.dao.repository.UserRepository;
import com.alinazim.ms.auth.mapper.factory.UserMapper;
import com.alinazim.ms.auth.model.request.LoginRequest;
import com.alinazim.ms.auth.model.request.RegisterRequest;
import com.alinazim.ms.auth.model.response.AuthResponse;
import com.alinazim.ms.auth.model.response.UserResponse;
import com.alinazim.ms.auth.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static com.alinazim.ms.auth.mapper.factory.UserMapper.USER_MAPPER;
import static com.alinazim.ms.auth.util.JwtUtil.JWT_UTIL;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    @Value("${private.key}")
    private String SECRET_KEY;


    public UserResponse register(RegisterRequest registerRequest) {
        var existingUser = userRepository.findByUsername(registerRequest.getUsername());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User is exist.");
        }

        String encodedPassword = new BCryptPasswordEncoder().encode(registerRequest.getPassword());

        userRepository.save(USER_MAPPER.buildUserEntity(registerRequest, encodedPassword));

        return USER_MAPPER.buildUserResponse(registerRequest);
    }


    public String login(LoginRequest loginRequest) {
        var user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found with username: %s".formatted(loginRequest.getUsername())));

        if (new BCryptPasswordEncoder().matches(loginRequest.getPassword(), user.getPassword())) {
            return "Bearer " + JWT_UTIL.generateToken(user, SECRET_KEY);
        } else {
            throw new RuntimeException("Password incorrect");
        }
    }

    public boolean verify(String token) {
        Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token);
        return true;
    }


    public AuthResponse getUser(String token) {
        var claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        var userEntity = userRepository.findById(Long.valueOf(claims.getSubject()))
                .orElseThrow(() -> new RuntimeException("User does not exist"));

        return AuthResponse.builder()
                .userId(userEntity.getId())
                .build();
    }
}
