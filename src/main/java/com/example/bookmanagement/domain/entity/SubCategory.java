package com.example.bookmanagement.domain.entity;

import com.example.bookmanagement.domain.entity.model.CategoryEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "sub_category")
public class SubCategory extends CategoryEntity {
    private Integer majorCategoryId;

}
