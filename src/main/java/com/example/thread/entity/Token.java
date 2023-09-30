package com.example.thread.entity;

import com.example.thread.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "token")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    User user;
    @Column(name = "value")
    String value;
    @CreationTimestamp
    @Column(name="created_date")
    LocalDate createdDate;
    @Column(name = "expired")
    LocalDate expired;
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    Status status = Status.ACTIVE;
}
