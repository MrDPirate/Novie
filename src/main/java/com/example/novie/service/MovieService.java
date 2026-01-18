package com.example.novie.service;

import com.example.novie.exception.InformationNotFoundException;
import com.example.novie.model.Category;
import com.example.novie.model.Movie;
import com.example.novie.repository.CategoryRepository;
import com.example.novie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieService {

    private MovieRepository movieRepository;
    private CategoryRepository categoryRepository;


    @Autowired
    public void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    // Create a new Movie
    public Movie createMovie(Movie movieObject) {
        System.out.println("Service Calling createMovie ==>");
        // Prevent duplicate movie name
        if (movieRepository.existsByNameIgnoreCase(movieObject.getName())) {
            throw new IllegalArgumentException(
                    "Movie already exists with name: " + movieObject.getName()
            );
        }

        // Attach MAIN category
        System.out.println("will call set category");
        if (movieObject.getCategory() != null) {
            System.out.println("Service got Category ==>");
            Long categoryId = movieObject.getCategory().getId();
            System.out.println("Got id " + categoryId);
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() ->
                            new InformationNotFoundException(
                                    "Category not found with id: " + categoryId
                            )
                    );
            movieObject.setCategory(category);
            System.out.println("set category");
        }

        // Attach SUB categories
        Set<Category> managedSubCategories = new HashSet<>();

        for (Category subCat : movieObject.getSubCategories()) {
            Category managed = categoryRepository.findById(subCat.getId())
                    .orElseThrow(() ->
                            new InformationNotFoundException(
                                    "Subcategory not found with id: " + subCat.getId()
                            )
                    );
            managedSubCategories.add(managed);
        }

        movieObject.setSubCategories(managedSubCategories);

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

        // Attach MAIN category (fetch from database)
        if (movieObject.getCategory() != null) {
            Long categoryId = movieObject.getCategory().getId();
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() ->
                            new InformationNotFoundException(
                                    "Category not found with id: " + categoryId
                            )
                    );
            existingMovie.setCategory(category);
        }

        // Attach SUB categories (fetch from database)
        Set<Category> managedSubCategories = new HashSet<>();
        if (movieObject.getSubCategories() != null) {
            for (Category subCat : movieObject.getSubCategories()) {
                Category managed = categoryRepository.findById(subCat.getId())
                        .orElseThrow(() ->
                                new InformationNotFoundException(
                                        "Subcategory not found with id: " + subCat.getId()
                                )
                        );
                managedSubCategories.add(managed);
            }
        }
        existingMovie.setSubCategories(managedSubCategories);

        movieRepository.save(existingMovie);
        
        // Re-fetch to ensure all relationships are properly loaded
        return getMovieById(movieId);
    }

    // Delete Movie
    public void deleteMovie(Long movieId) {

        Movie movie = getMovieById(movieId);
        movieRepository.delete(movie);
    }
}
