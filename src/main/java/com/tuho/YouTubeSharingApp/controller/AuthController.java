package com.tuho.YouTubeSharingApp.controller;

import com.tuho.YouTubeSharingApp.Request.UserRegisterReq;
import com.tuho.YouTubeSharingApp.Response.LoginRes;
import com.tuho.YouTubeSharingApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterReq registerReq) {
        userService.registerUser(registerReq);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRes> login(@RequestBody UserRegisterReq registerReq) {
        LoginRes res = userService.loginUser(registerReq);
        return ResponseEntity.ok(res);
    }
}