package com.university.lms.admin.service;

import com.university.lms.common.model.User;
import com.university.lms.common.dto.LoginRequest;
import com.university.lms.common.dto.LoginResponse;
import com.university.lms.common.security.JwtUtil;
import com.university.lms.common.exception.UnauthorizedException;
import com.university.lms.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UnauthorizedException("Invalid credentials"));
        
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Invalid credentials");
        }
        
        if (!user.isEnabled()) {
            throw new UnauthorizedException("Account is disabled");
        }
        
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name(), user.getId());
        
        return new LoginResponse(token, user.getUsername(), user.getRole().name(), user.getId());
    }
    
    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}