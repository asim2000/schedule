package com.example.thread.service;

import com.example.thread.dto.UserDto;
import com.example.thread.entity.User;

public interface AuthService {
    void register(UserDto user);

    void confirm(Long id,String token);
}
