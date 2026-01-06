package com.example.novie.controller;

import com.example.novie.model.Completed;
import com.example.novie.service.CompletedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/users/tracking/")
public class CompletedController {
    private CompletedService completedService;

    @Autowired
    public void setCompletedService(CompletedService completedService){
        this.completedService=completedService;
    }

    @PostMapping
    public Completed createCompleted(@RequestBody Completed completedObject){
        System.out.println("Controller Calling createCompleted ==>");
        return completedService.createCompleted(completedObject);
    }

    @GetMapping
    public Completed getCompleted(){
        System.out.println("Controller Calling getCompleted ==>");
        return completedService.getCompleted();
    }

    @PutMapping
    public Completed updateCompleted(@RequestBody Completed completedObject){
        System.out.println("Controller Calling updateCompleted ==>");
        return completedService.updateCompleted(completedObject);
    }
}
