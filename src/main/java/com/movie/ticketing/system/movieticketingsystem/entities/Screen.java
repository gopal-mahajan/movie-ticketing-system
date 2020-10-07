package com.movie.ticketing.system.movieticketingsystem.entities;

import javax.persistence.*;

@Entity
@Table(name = "screen")
public class Screen {
    private Long theaterId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private Long movieId;
    private String time;

    public Screen(Long theaterId) throws Exception {
        if (theaterId > 0)
            this.theaterId = theaterId;
        else
            throw new Exception("Theater id invalid");

    }

    public Screen(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTheaterId() {
        return theaterId;
    }


    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
