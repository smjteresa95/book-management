package com.example.bookmanagement.domain.repository;

import com.example.bookmanagement.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
}
