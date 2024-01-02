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
@Table(name = "major_category")
public class MajorCategory {
    @Id
    private Long id;
    private Long subCategoryId;
    private String category;

}
