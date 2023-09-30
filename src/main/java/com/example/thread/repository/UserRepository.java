package com.example.thread.repository;

import com.example.thread.entity.User;
import com.example.thread.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    List<User> findByStatus(AccountStatus saved);
}
