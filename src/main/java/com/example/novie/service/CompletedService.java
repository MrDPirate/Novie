package com.example.novie.service;

import com.example.novie.exception.InformationExistException;
import com.example.novie.exception.InformationNotFoundException;
import com.example.novie.model.Completed;
import com.example.novie.model.User;
import com.example.novie.repository.CompletedRepository;
import com.example.novie.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CompletedService {
    private CompletedRepository completedRepository;

    @Autowired
    public void setCompletedRepository(CompletedRepository completedRepository){
        this.completedRepository=completedRepository;
    }

    private static User getCurrentLoggedInUser(){
        MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    //Create
    public Completed createCompleted(Completed completedObject){
        System.out.println("Service Calling createCompleted ==>");
        Completed completed = completedRepository.findByUserId(getCurrentLoggedInUser().getId())
                .orElseThrow(()->
                        new InformationExistException("User already has a Completed"));

        completedObject.setUser(getCurrentLoggedInUser());
        return completedRepository.save(completedObject);

    }

    //Get
    public Completed getCompleted(){
        System.out.println("Service Calling getCompleted ==>");
        return completedRepository.findByUserId(getCurrentLoggedInUser().getId())
                .orElseThrow(()->
                        new InformationExistException("User already has a Completed"));

    }

    //Update
    public Completed updateCompleted(Completed completedObject){
        System.out.println("Service Calling updateCompleted ==>");
        Completed existCompleted = completedRepository.findByUserId(getCurrentLoggedInUser().getId())
                .orElseThrow(()->
                        new InformationExistException("User already has a Completed"));

        existCompleted.setMovies(completedObject.getMovies());
        completedRepository.save(existCompleted);
        return existCompleted;

    }
}
