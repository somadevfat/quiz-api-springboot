package com.example.quiz_api.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.quiz_api.domain.User;
import com.example.quiz_api.dto.UserDto;
import com.example.quiz_api.repository.UserRepository;
import com.example.quiz_api.util.JwtUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Validated
@Tag(name = "Authentication", description = "ユーザー認証API")
public class UserRegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserRegistrationController(UserRepository userRepository, 
                                    PasswordEncoder passwordEncoder, 
                                    JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    @Operation(summary = "ユーザー登録", description = "新しいユーザーを登録します")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "ユーザー登録成功"),
        @ApiResponse(responseCode = "409", description = "ユーザー名が既に存在します"),
        @ApiResponse(responseCode = "400", description = "入力データが無効です")
    })
    public ResponseEntity<Map<String, Object>> register(
        @Parameter(description = "ユーザー登録情報", required = true)
        @Valid @RequestBody UserDto userDto) {
        // Check if username already exists
        if (userRepository.findByUsername(userDto.username()).isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Username already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        // Create new user
        User user = new User();
        user.setUsername(userDto.username());
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user.setCreatedAt(LocalDateTime.now());
        
        User savedUser = userRepository.save(user);

        // Generate JWT token
        String token = jwtUtil.generateToken(savedUser.getUsername());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered successfully");
        response.put("username", savedUser.getUsername());
        response.put("token", token);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    @Operation(summary = "ユーザーログイン", description = "ユーザー認証を行いJWTトークンを取得します")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "ログイン成功"),
        @ApiResponse(responseCode = "401", description = "認証情報が無効です"),
        @ApiResponse(responseCode = "400", description = "入力データが無効です")
    })
    public ResponseEntity<Map<String, Object>> login(
        @Parameter(description = "ログイン情報", required = true)
        @Valid @RequestBody UserDto userDto) {
        User user = userRepository.findByUsername(userDto.username())
                .orElse(null);

        if (user == null || !passwordEncoder.matches(userDto.password(), user.getPassword())) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        String token = jwtUtil.generateToken(user.getUsername());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("username", user.getUsername());
        response.put("token", token);
        
        return ResponseEntity.ok(response);
    }
}