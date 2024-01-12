package com.example.bookmanagement.domain.repository;

import com.example.bookmanagement.domain.entity.Book;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Lock(value = LockModeType.OPTIMISTIC)
    @Query("SELECT b from Book b WHERE b.id = :id")
    Optional<Book> findByIdOptimistic(@Param("id") Long id);

}
