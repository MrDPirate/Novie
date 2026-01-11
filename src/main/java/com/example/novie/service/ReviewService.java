package com.example.novie.service;

import com.example.novie.exception.InformationExistException;
import com.example.novie.exception.InformationNotFoundException;
import com.example.novie.model.Completed;
import com.example.novie.model.Movie;
import com.example.novie.model.Review;
import com.example.novie.model.User;
import com.example.novie.repository.MovieRepository;
import com.example.novie.repository.ReviewRepository;
import com.example.novie.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;
    private MovieRepository movieRepository;

    @Autowired
    public void setReviewRepository(ReviewRepository reviewRepository){
        this.reviewRepository=reviewRepository;
    }

    private static User getCurrentLoggedInUser(){
        MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    //Create Review
    public Review createReview(Long movieId,Review reviewObject){
        System.out.println("Service Calling createReview ==>");
        reviewObject.setUser(getCurrentLoggedInUser());
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(()->new InformationNotFoundException("No movie with this id"));
        reviewObject.setMovie(movie);
        return reviewRepository.save(reviewObject);
    }

    //Get By movie id
    public List<Review> getReviewByMovieId(Long movieId){
            System.out.println("Service Calling getReviewByUserId ==>");
            return reviewRepository.findByMovieId(getCurrentLoggedInUser().getId());
    }

    //Get by user id
    public List<Review> getReviewByUserId(){
        System.out.println("Service Calling getReviewByUserId ==>");
        return reviewRepository.findByUserId(getCurrentLoggedInUser().getId());
    }

    //Get by review id
    public Review getReview(Long id){
        System.out.println("Service Calling getReviewByUserId ==>");
        return reviewRepository.findById(id).orElseThrow(()->
                new InformationExistException("No review with this id"));
    }

    //Get by user and movie
    public List<Review> getReviewByUserIdAndMovieId(Long movieId){
        System.out.println("Service Calling getReviewByUserIdAndMovieId ==>");
        return reviewRepository.findByUserIdAndMovieId(getCurrentLoggedInUser().getId(),movieId);
    }

    //Delete review by id
    public Review deleteReview(Long id){
        System.out.println("Service Calling deleteReview ==>");
        Review review = reviewRepository.findById(id).orElseThrow(()->
                new InformationExistException("No review with this id"));
        reviewRepository.deleteById(id);
        return review;
    }

    //Mass delete
    public List<Review> massDeleteReview(List<Long> reviewIds){
        System.out.println("Service Calling massDeleteReview ==>");
        List<Review> reviewList = reviewRepository.findAllById(reviewIds);
        reviewRepository.deleteAllById(reviewIds);
        return reviewList;
    }
}
