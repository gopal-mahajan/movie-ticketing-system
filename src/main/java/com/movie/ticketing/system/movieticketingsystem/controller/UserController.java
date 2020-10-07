package com.movie.ticketing.system.movieticketingsystem.controller;


import com.movie.ticketing.system.movieticketingsystem.entities.Movies;
import com.movie.ticketing.system.movieticketingsystem.entities.User;
import com.movie.ticketing.system.movieticketingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/user")
    User addUser(@RequestParam("Name") String name,@RequestParam("mobile") String mobile,@RequestParam("email") String email){
    User user=new User(name,mobile,email);

   return userRepository.save(user);
    }



}
