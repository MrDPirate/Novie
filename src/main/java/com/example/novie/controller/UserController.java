package com.example.novie.controller;

import com.example.novie.model.Completed;
import com.example.novie.model.User;
import com.example.novie.model.Wishlist;
import com.example.novie.model.request.ChangePasswordRequest;
import com.example.novie.model.request.LoginRequest;
import com.example.novie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth/users")
public class UserController {
    private UserService userService;

    @Autowired
    private void setUserService(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User userObject){
        System.out.println("Calling create user");
        Wishlist wishlist =new Wishlist();
        wishlist.setUser(userObject);
        userObject.setWishlist(wishlist);
        Completed completed = new Completed();
        completed.setUser(userObject);
        userObject.setCompleted(completed);
        return userService.createUser(userObject);

    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        System.out.println("calling loginUser ----->");;
        return userService.loginUser(loginRequest);
    }

    @PutMapping("/change-password")
    public void changePassword(@RequestBody ChangePasswordRequest request){
        System.out.println("calling change password in controller ========>");
        userService.changePassword(request.getOldPass(), request.getNewPass() );
    }
    @GetMapping("/register/verify")
    public void validate(@RequestParam String token){
        System.out.println("calling change password in controller ========>");
        userService.validate(token);

    }
}
