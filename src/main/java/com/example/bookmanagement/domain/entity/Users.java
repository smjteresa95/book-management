package com.example.bookmanagement.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "user")
public class Users {
    @Id
    private Long id;
    private String role;
    private String userName;
    private String password;
    private String name;
    private String email;
    private String phoneNum;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
