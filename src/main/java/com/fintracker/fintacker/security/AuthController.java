package com.fintracker.fintacker.security;

import com.fintracker.fintacker.Service.UserService;
import com.fintracker.fintacker.dto.RegisterRequestDTO;
import com.fintracker.fintacker.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // хэш
        user.setEmail(dto.getEmail());
        userService.save(user);
        return ResponseEntity.status(201).body("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RegisterRequestDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );
        String token = jwtTokenProvider.generateToken(dto.getUsername());
        return ResponseEntity.ok(token);
    }
}
