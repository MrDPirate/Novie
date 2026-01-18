package com.example.novie.repository;

import com.example.novie.model.Movie;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    // Load category and subCategories eagerly when fetching all movies
    @EntityGraph(attributePaths = {"category", "subCategories"})
    List<Movie> findAll();

    // Load category and subCategories eagerly when fetching by ID
    @EntityGraph(attributePaths = {"category", "subCategories"})
    Optional<Movie> findById(Long id);

    // Duplicate movie prevention
    Optional<Movie> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
}
