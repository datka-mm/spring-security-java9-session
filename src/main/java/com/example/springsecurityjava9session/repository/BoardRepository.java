package com.example.springsecurityjava9session.repository;

import com.example.springsecurityjava9session.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}