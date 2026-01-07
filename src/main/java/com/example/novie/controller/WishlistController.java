package com.example.novie.controller;

import com.example.novie.model.Wishlist;
import com.example.novie.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/wishlist")
public class WishlistController {

    private WishlistService wishlistService;

    @Autowired
    public void setWishlistService(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    /**
     * Create wishlist for logged-in user
     */
    @PostMapping
    public Wishlist createWishlist() {
        return wishlistService.createWishlist();
    }

    /**
     * Get logged-in user's wishlist
     */
    @GetMapping
    public Wishlist getWishlist() {
        return wishlistService.getWishlist();
    }

    /**
     * Add movie to wishlist
     */
    @PostMapping("/add/{movieId}")
    public Wishlist addMovie(@PathVariable Long movieId) {
        return wishlistService.addMovieToWishlist(movieId);
    }

    /**
     * Remove movie from wishlist
     */
    @DeleteMapping("/remove/{movieId}")
    public Wishlist removeMovie(@PathVariable Long movieId) {
        return wishlistService.removeMovieFromWishlist(movieId);
    }
}
