package com.example.thread.controller;

import com.example.thread.dto.UserDto;
import com.example.thread.entity.User;
import com.example.thread.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("register")
    public void register(@RequestBody UserDto user){
        authService.register(user);
    }
    @GetMapping("user/{id}/confirm/{token}")
    public void confirm(@RequestParam Long id,@RequestParam String token){
        authService.confirm(id,token);
    }
}
