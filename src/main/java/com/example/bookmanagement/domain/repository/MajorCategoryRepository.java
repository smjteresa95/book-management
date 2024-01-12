package com.example.bookmanagement.domain.repository;

import com.example.bookmanagement.domain.entity.MajorCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorCategoryRepository extends JpaRepository<MajorCategory, Long> {
}
