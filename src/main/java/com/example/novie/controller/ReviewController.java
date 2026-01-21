package com.example.novie.controller;

import com.example.novie.model.Review;
import com.example.novie.service.CompletedService;
import com.example.novie.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/")
public class ReviewController {
    private ReviewService reviewService;

    @Autowired
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping(path = "{movieId}/reviews")
    public Review createReview(@PathVariable(value = "movieId") Long movieId,@RequestBody Review reviewObject) {
        System.out.println("Controller Calling createReview ==>");
        return reviewService.createReview(movieId,reviewObject);
    }

    @GetMapping(path = "{movieId}/reviews")
    public List<Review> getReviewByMovieId(@PathVariable(value = "movieId") Long movieId) {
        System.out.println("Controller Calling getReviewByMovieId ==>");
        return reviewService.getReviewByMovieId(movieId);
    }

    @GetMapping(path = "users/reviews")
    public List<Review> getReviewByUserId() {
        System.out.println("Controller Calling getReviewByUserId ==>");
        return reviewService.getReviewByUserId();
    }

    @GetMapping(path = "reviews/{reviewId}")
    public Review getReview(@PathVariable(value = "reviewId") Long id) {
        System.out.println("Controller Calling getReview ==>");
        return reviewService.getReview(id);
    }

    @GetMapping(path = "users/reviews/{movieId}")
    public List<Review> getReviewByUserIdAndMovieId(@PathVariable(value = "movieId") Long movieId) {
        System.out.println("Controller Calling getReviewByUserIdAndMovieId ==>");
        return reviewService.getReviewByUserIdAndMovieId(movieId);
    }

    @DeleteMapping(path = "reviews/{id}")
    public Review deleteReview(@PathVariable(value = "id") Long id) {
        System.out.println("Controller Calling getReviewByUserIdAndMovieId ==>");
        return reviewService.deleteReview(id);
    }

    @DeleteMapping(path = "reviews")
    public List<Review> massDeleteReview(@RequestBody List<Long> reviewIds) {
        System.out.println("Controller Calling massDeleteReview ==>");
        return reviewService.massDeleteReview(reviewIds);
    }
}