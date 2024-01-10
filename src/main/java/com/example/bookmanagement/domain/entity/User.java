package com.example.bookmanagement.domain.entity;

import com.example.bookmanagement.enums.ERole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "userName"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    @Column(nullable = false)
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNum;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING) // Use EnumType.STRING to store the enum values as strings
    @Column(name = "role_name")
    private Set<ERole> roles = new HashSet<>();

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
}
