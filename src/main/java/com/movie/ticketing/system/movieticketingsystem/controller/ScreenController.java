package com.movie.ticketing.system.movieticketingsystem.controller;

import com.movie.ticketing.system.movieticketingsystem.entities.Screen;
import com.movie.ticketing.system.movieticketingsystem.entities.Theater;
import com.movie.ticketing.system.movieticketingsystem.repository.MovieRepository;
import com.movie.ticketing.system.movieticketingsystem.repository.ScreenRepository;
import com.movie.ticketing.system.movieticketingsystem.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ScreenController {
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    ScreenRepository screenRepository;
    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/screen/")
    Map<Theater, String> getTheaterandTime(@RequestParam("movieid") Long movieid, @RequestParam("location") String location) {
        List<Theater> theaters = theaterRepository.findByLocation(location.toUpperCase());
        Map<Long,Theater> map=new HashMap<>();
        for (Theater i : theaters) {
            map.put(i.getId(),i);
        }
        List<Screen> screen = screenRepository.getScreenByTheaterAndMovie(map.keySet(), movieid);
        Map<Theater, String> result = new HashMap<>();
        for (Screen s : screen) {
            result.put(map.get(s.getTheaterId()),s.getTime());
        }
        return result;
    }
}
