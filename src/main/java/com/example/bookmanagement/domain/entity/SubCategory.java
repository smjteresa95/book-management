package com.example.bookmanagement.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "sub_category")
public class SubCategory {
    @Id
    private Long id;
    private String category;

}
