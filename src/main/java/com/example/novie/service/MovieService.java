package com.example.novie.service;

import com.example.novie.exception.InformationNotFoundException;
import com.example.novie.model.Movie;
import com.example.novie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Create a new Movie
    public Movie createMovie(Movie movieObject) {
        return movieRepository.save(movieObject);
    }

    // Get All Movies
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // Get Movie by id
    public Movie getMovieById(Long movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() ->
                        new InformationNotFoundException(
                                "Movie not found with id: " + movieId
                        ));
    }

    // Update Movie
    public Movie updateMovie(Long movieId, Movie movieObject) {

        Movie existingMovie = getMovieById(movieId);

        existingMovie.setName(movieObject.getName());
        existingMovie.setDescription(movieObject.getDescription());
        existingMovie.setYear(movieObject.getYear());
        existingMovie.setCategory(movieObject.getCategory());
        existingMovie.setSubCategories(movieObject.getSubCategories());

        return movieRepository.save(existingMovie);
    }

    // Delete Movie
    public void deleteMovie(Long movieId) {

        Movie movie = getMovieById(movieId);
        movieRepository.delete(movie);
    }
}
