package com.example.novie.service;

import com.example.novie.exception.InformationExistException;
import com.example.novie.exception.InformationNotFoundException;
import com.example.novie.model.Movie;
import com.example.novie.model.User;
import com.example.novie.model.Wishlist;
import com.example.novie.repository.MovieRepository;
import com.example.novie.repository.WishlistRepository;
import com.example.novie.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class WishlistService {

    private WishlistRepository wishlistRepository;
    private MovieRepository movieRepository;

    @Autowired
    public void setWishlistRepository(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Autowired
    public void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * Get currently authenticated user
     */
    private static User getCurrentLoggedInUser() {
        MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal();
        return userDetails.getUser();
    }

    /**
     * Create wishlist for logged-in user (only once)
     */
    public Wishlist createWishlist() {

        Long userId = getCurrentLoggedInUser().getId();

        if (wishlistRepository.findByUserId(userId).isPresent()) {
            throw new InformationExistException("Wishlist already exists for this user.");
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setUser(getCurrentLoggedInUser());
        wishlist.setMovies(new HashSet<>());

        return wishlistRepository.save(wishlist);
    }

    /**
     * Get wishlist for logged-in user
     */
    public Wishlist getWishlist() {
        return wishlistRepository.findByUserId(getCurrentLoggedInUser().getId())
                .orElseThrow(() ->
                        new InformationNotFoundException("Wishlist not found for this user."));
    }

    /**
     * Add movie to wishlist
     */
    public Wishlist addMovieToWishlist(Long movieId) {

        Wishlist wishlist = getWishlist();

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() ->
                        new InformationNotFoundException("Movie not found with id: " + movieId));

        if (wishlist.getMovies().contains(movie)) {
            throw new InformationExistException("Movie already exists in wishlist.");
        }

        wishlist.getMovies().add(movie);
        return wishlistRepository.save(wishlist);
    }

    /**
     * Remove movie from wishlist
     */
    public Wishlist removeMovieFromWishlist(Long movieId) {

        Wishlist wishlist = getWishlist();

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() ->
                        new InformationNotFoundException("Movie not found with id: " + movieId));

        if (!wishlist.getMovies().contains(movie)) {
            throw new InformationNotFoundException("Movie not found in wishlist.");
        }

        wishlist.getMovies().remove(movie);
        return wishlistRepository.save(wishlist);
    }
}
