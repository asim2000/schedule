package com.example.thread.repository;

import com.example.thread.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token,Long> {
    @Query("select t from Token t where current_date > t.expired")
    List<Token> findAllExpiredToken();
}
