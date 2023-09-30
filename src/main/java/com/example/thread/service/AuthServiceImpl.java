package com.example.thread.service;

import com.example.thread.dto.UserDto;
import com.example.thread.entity.Token;
import com.example.thread.entity.User;
import com.example.thread.enums.AccountStatus;
import com.example.thread.repository.TokenRepository;
import com.example.thread.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final TokenRepository tokenRepository;
    @Override
    public void register(UserDto user) {
       userRepository.save(getUser(user));
    }

    @Override
    public void confirm(Long id,String token) {
        User u = userRepository.findById(id).get();
        AtomicReference<Token> tok = null;
        u.getTokens().stream().forEach(t->{
           if(LocalDate.now().isBefore(t.getExpired()) || t.getValue().equals(token))
               tok.set(t);
        });
        if(tok.get() !=null){
            u.setStatus(AccountStatus.COMPLETE);
            userRepository.save(u);
        }
    }

    private User getUser(UserDto user){
        return
                User.builder()
                        .name(user.getName())
                        .surname(user.getSurname())
                        .username(user.getUsername())
                        .password(user.getPassword()).build();
    }
}
