package com.movie.ticketing.system.movieticketingsystem.controller;

import com.movie.ticketing.system.movieticketingsystem.entities.Movies;
import com.movie.ticketing.system.movieticketingsystem.entities.Screen;
import com.movie.ticketing.system.movieticketingsystem.entities.Seats;
import com.movie.ticketing.system.movieticketingsystem.entities.Theater;
import com.movie.ticketing.system.movieticketingsystem.exception.MovieTimeOverlappingException;
import com.movie.ticketing.system.movieticketingsystem.repository.MovieRepository;
import com.movie.ticketing.system.movieticketingsystem.repository.ScreenRepository;
import com.movie.ticketing.system.movieticketingsystem.repository.SeatsRepository;
import com.movie.ticketing.system.movieticketingsystem.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class MovieController {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    ScreenRepository screenRepository;
    @Autowired
    private SeatsRepository seatsRepository;

    @PostMapping("/movie")
    Movies addMovie(@RequestParam("Movie") String movie, @RequestParam("Cast") String cast,
                    @RequestParam("Rating") int rating, @RequestParam("Summary") String summary,
                    @RequestParam("Year") int year, @RequestParam("movieLength") Integer movieLength) {
        Movies movies = new Movies(movie, cast, summary, rating, year, movieLength);
        return movieRepository.save(movies);
    }

    @PostMapping("PostMapping/schedule/movie")
    @Transactional
    ResponseEntity scheduleMovies(@RequestParam("movie_id") Long movieId, @RequestParam("theater_id") Long theaterId,
                                  @RequestParam("screen_id") Long screenId, @RequestParam("time") String time)
            throws Exception {
        try{
        String[] timeArr = time.split(",");
        List<LocalTime> times = new ArrayList<>();
        Movies movies = movieRepository.findById(movieId).orElse(null);
        for (String t : timeArr) {
            times.add(LocalTime.parse(t));
        }
        Collections.sort(times);
        for (int i = 1; i < times.size(); i++) {
            LocalTime from = times.get(i - 1);
            LocalTime to = times.get(i);
            Duration d = Duration.between(from, to);
            if (d.toMinutes() <= movies.getMovieLength()) {
                throw new MovieTimeOverlappingException("this schedule is not possible");
            }
        }
        Screen screen = screenRepository.findByTheaterIdAndId(theaterId, screenId);
        screen.setMovieId(movieId);
        screen.setTime(time);
        screenRepository.save(screen);

        Theater theater = theaterRepository.findById(screen.getTheaterId()).orElse(null);

        if (theater != null) {
            for (int j = 0; j < theater.getSeats(); j++) {
                for (int i = 0; i < times.size(); i++) {
                    Seats seats1 = new Seats((long) (j+1),theater.getId(), screen.getId());
                    seats1.setStarttime(times.get(i));
                    seats1.setEndtime(times.get(i).plusMinutes(movies.getMovieLength()));
                    seatsRepository.save(seats1);
                }
            }
        }
        }catch (MovieTimeOverlappingException e){
            return new ResponseEntity("this schedule is not possible", HttpStatus.CONFLICT);
        }
        return null;
    }


    @GetMapping("/location/movie")
    List<Movies> getMoviesByLocation(@RequestParam("Location") String location) {
        List<Theater> theaters = theaterRepository.findByLocation(location.toUpperCase());
        List<Long> theaterIds = new ArrayList<>();
        for (Theater t : theaters) {
            theaterIds.add(t.getId());
        }
        List<Long> movieIds = screenRepository.getMovieByTheaterId(theaterIds);
        return movieRepository.getMoviebyId(movieIds);
    }
}
