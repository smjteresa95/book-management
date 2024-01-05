package com.example.bookmanagement.domain.repository;

import com.example.bookmanagement.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface BookRepository extends JpaRepository<Book, Serializable> {
}
