package com.example.bookmanagement.domain.entity;

import com.example.bookmanagement.domain.entity.model.CategoryEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor

@Entity
@Table(name = "major_category")
public class MajorCategory extends CategoryEntity {
}
