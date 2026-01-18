package com.example.novie.repository;

import com.example.novie.model.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    @EntityGraph(attributePaths = {"movie", "movie.category", "movie.subCategories"})
    List<Review> findByUserId(Long id);
    
    @EntityGraph(attributePaths = {"movie", "movie.category", "movie.subCategories"})
    List<Review> findByMovieId(Long movieId);
    
    @EntityGraph(attributePaths = {"movie", "movie.category", "movie.subCategories"})
    List<Review> findByUserIdAndMovieId(Long userId, Long movieId);
}
