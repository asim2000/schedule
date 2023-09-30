package com.example.thread.schedule;

import com.example.thread.entity.Token;
import com.example.thread.entity.User;
import com.example.thread.enums.AccountStatus;
import com.example.thread.enums.Status;
import com.example.thread.repository.TokenRepository;
import com.example.thread.repository.UserRepository;
import com.example.thread.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@RequiredArgsConstructor
@Component
public class Schedule {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final TokenRepository tokenRepository;

    @Scheduled(cron = "*/10 * * * * *")
    public void take(){
        List<User> users = userRepository.findByStatus(AccountStatus.SAVED);
        users.stream().forEach(user -> {
            user.setStatus(AccountStatus.TAKED);
            userRepository.save(user);
        });

    }
    @Scheduled(cron = "*/10 * * * * *")
    public void sendMail(){
        List<User> users = userRepository.findByStatus(AccountStatus.TAKED);
        users.stream().forEach(user -> {
            String token = String.valueOf(UUID.randomUUID());
            Token t = new Token();
            t.setUser(user);
            t.setValue(token);
            t.setExpired(LocalDate.now().plusDays(1));
            tokenRepository.save(t);
            emailService.sendEmail(user.getUsername(),"Account submit","<a href='http://localhost:8083/auth/user/${u.getId()}confirm/${token}'>Confirm your account</a>");
            user.setStatus(AccountStatus.PENDING);
            userRepository.save(user);
        });
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void inActiveUser(){
        List<Token> expiredTokens = tokenRepository.findAllExpiredToken();
        expiredTokens.stream().forEach(token -> {
            token.setStatus(Status.DEACTIVE);
            tokenRepository.save(token);
        });
    }
}
