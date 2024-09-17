package com.tuho.YouTubeSharingApp.service;

import com.tuho.YouTubeSharingApp.Request.UserRegisterReq;
import com.tuho.YouTubeSharingApp.Response.LoginRes;
import com.tuho.YouTubeSharingApp.entity.User;
import com.tuho.YouTubeSharingApp.repository.UserRepository;
import com.tuho.YouTubeSharingApp.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void registerUser(UserRegisterReq req) {
        if(userRepository.findByUsername(req.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword())); // Encode password
        userRepository.save(user);
    }

    public LoginRes loginUser(UserRegisterReq req) {
        Optional<User> user = userRepository.findByUsername(req.getUsername());
        if(!user.isPresent()) {
            throw new IllegalArgumentException("Username does not exist");
        }

        if(!passwordEncoder.matches(req.getPassword(), user.get().getPassword())) {
            throw new IllegalArgumentException("Password is incorrect");
        }
        String token = jwtUtil.generateToken(req.getUsername());
        return LoginRes.builder().token(token).uid(user.get().getId()).username(user.get().getUsername()).build();
    }
}