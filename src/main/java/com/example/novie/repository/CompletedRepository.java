package com.example.novie.repository;

import com.example.novie.model.Completed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompletedRepository extends JpaRepository<Completed, Long> {
    Optional<Completed> findByUserId(Long userId);

}
