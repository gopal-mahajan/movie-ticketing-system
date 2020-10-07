package com.movie.ticketing.system.movieticketingsystem.controller;

import com.movie.ticketing.system.movieticketingsystem.entities.Screen;
import com.movie.ticketing.system.movieticketingsystem.entities.Theater;
import com.movie.ticketing.system.movieticketingsystem.repository.ScreenRepository;
import com.movie.ticketing.system.movieticketingsystem.repository.SeatsRepository;
import com.movie.ticketing.system.movieticketingsystem.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class TheaterController {
    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ScreenRepository screenRepository;
    @Autowired
    SeatsRepository seatsRepository;

    @PostMapping("/theater")
    Theater addTheater(@RequestParam("Seats") Long seats,
                       @RequestParam("Screen") Long screen,
                       @RequestParam("Name") String name, @RequestParam("Location") String location) throws Exception {
        Theater theater = new Theater(seats, screen, name, location);
        theater = theaterRepository.save(theater);
        for (int i = 0; i < screen; i++) {
            Screen screen1 = new Screen(theater.getId());
            screenRepository.save(screen1);
        }
        return theater;
    }

    @GetMapping("/location/all")
    List<String> getAllLocations() {
        return theaterRepository.findDistinctLocation();
    }
}
