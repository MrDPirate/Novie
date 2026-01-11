package com.example.novie.repository;

import com.example.novie.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    Optional<Review> findByUserId(Long id);
    List<Review> findByMovieId(Long movieId);
}
