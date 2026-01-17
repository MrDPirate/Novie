package com.example.novie.controller;

import com.example.novie.model.Movie;
import com.example.novie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")

public class MovieController {

    private MovieService movieService;


    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    // Create a Movie
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Movie createMovie(@RequestBody Movie movieObject) {
        return movieService.createMovie(movieObject);
    }

    // Get all Movies
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    // Get Movie by id
    @GetMapping("/{movieId}")
    public Movie getMovieById(@PathVariable Long movieId) {
        return movieService.getMovieById(movieId);
    }

    // Update Movie
    @PutMapping("/{movieId}")
    public Movie updateMovie(@PathVariable Long movieId, @RequestBody Movie movieObject) {
        return movieService.updateMovie(movieId, movieObject);
    }

    // Delete Movie
    @DeleteMapping("/{movieId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteMovie(@PathVariable Long movieId) {
        movieService.deleteMovie(movieId);
    }



}
