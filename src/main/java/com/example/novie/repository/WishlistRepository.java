package com.example.novie.repository;

import com.example.novie.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    // Crucial for finding the wishlist belonging to the logged-in user
    Optional<Wishlist> findByUserId(Long userId);

}