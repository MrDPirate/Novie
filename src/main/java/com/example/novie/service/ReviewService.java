package com.example.novie.service;

import com.example.novie.exception.InformationExistException;
import com.example.novie.model.Completed;
import com.example.novie.model.Review;
import com.example.novie.model.User;
import com.example.novie.repository.ReviewRepository;
import com.example.novie.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;

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
    public Review createReview(Review reviewObject){
        System.out.println("Service Calling createReview ==>");
        Review review = reviewRepository.findByUserId(getCurrentLoggedInUser().getId())
                .orElseThrow(()->
                        new InformationExistException("User already has a Completed"));

        reviewObject.setUser(getCurrentLoggedInUser());
        return reviewRepository.save(reviewObject);
    }

    //Get By movie Id

    //Get by user id
}
