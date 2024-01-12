package com.example.bookmanagement.domain.repository;

import com.example.bookmanagement.domain.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
}
