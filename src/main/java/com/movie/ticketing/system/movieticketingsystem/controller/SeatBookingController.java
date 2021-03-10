package com.movie.ticketing.system.movieticketingsystem.controller;

import com.movie.ticketing.system.movieticketingsystem.service.SeatBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
public class SeatBookingController {

    @Autowired
    SeatBookingService seatBookingService;

    @PutMapping("/booking/{user_id}")
    ResponseEntity seatBoookingStatus(@PathVariable("user_id") Long userId,
                                      @RequestParam("ScreenId") Long screen_id,
                                      @RequestParam("Movie_Id") Long movie_id,
                                      @RequestParam("Time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime time,
                                      @RequestParam("Date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                      @RequestParam("Seats") List<Long> seats)  {
        try {
            return ResponseEntity.ok(seatBookingService.bookSeat(screen_id, seats, time, date, userId, movie_id));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
