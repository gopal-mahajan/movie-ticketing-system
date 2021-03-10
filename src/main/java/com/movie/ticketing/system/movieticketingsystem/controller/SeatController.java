package com.movie.ticketing.system.movieticketingsystem.controller;

import com.movie.ticketing.system.movieticketingsystem.entities.Screen;
import com.movie.ticketing.system.movieticketingsystem.entities.Seats;
import com.movie.ticketing.system.movieticketingsystem.repository.ScreenRepository;
import com.movie.ticketing.system.movieticketingsystem.repository.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
public class SeatController {
    @Autowired
    ScreenRepository screenRepository;

    @Autowired
    SeatsRepository seatsRepository;

    @GetMapping("/seat")
    List<Seats> getSeatbyMovie(@RequestParam("movieid") Long movie_id,
                               @RequestParam("theater_id") Long theater_id,
                               @RequestParam("time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime time,
                               @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date) throws ParseException {
        Screen screen = screenRepository.getScreenByTheaterAndMovie1(theater_id, movie_id);
        return seatsRepository.getSeatbyMovie(screen.getId(),  time, date);
    }
}
