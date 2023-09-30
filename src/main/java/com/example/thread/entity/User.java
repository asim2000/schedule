package com.example.thread.entity;

import com.example.thread.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "surname")
    String surname;
    @Column(name = "username")
    String username;
    @Column(name = "password")
    String password;
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    AccountStatus status= AccountStatus.SAVED;
    @OneToMany(mappedBy = "user")
    List<Token> tokens;
    @CreationTimestamp
    @Column(name="created_date")
    LocalDate createdDate;
}
