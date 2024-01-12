package com.example.bookmanagement.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "sub_category")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer majorCategoryId;
    private String category;

}
