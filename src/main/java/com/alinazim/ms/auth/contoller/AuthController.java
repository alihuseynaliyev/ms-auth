package com.alinazim.ms.auth.contoller;

import com.alinazim.ms.auth.model.request.LoginRequest;
import com.alinazim.ms.auth.model.request.RegisterRequest;
import com.alinazim.ms.auth.model.response.AuthResponse;
import com.alinazim.ms.auth.model.response.UserResponse;
import com.alinazim.ms.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("/verify")
    public Boolean verify(@RequestParam String token) {
        return authService.verify(token);
    }

    @GetMapping("/users")
    public AuthResponse getUser(@RequestParam String token){
        return authService.getUser(token);
    }
}