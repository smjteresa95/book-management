package com.example.bookmanagement.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "major_category")
public class MajorCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String category;
}
