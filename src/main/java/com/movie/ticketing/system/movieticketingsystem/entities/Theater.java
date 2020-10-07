package com.movie.ticketing.system.movieticketingsystem.entities;

import javax.persistence.*;

@Entity
@Table(name = "theater")
public class Theater {
    private String name;
    private Long screen;
    private String location;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long seats;

    public Theater(Long seats, Long screen, String name, String location) throws Exception {
        if (seats > 0)
            this.seats = seats;
        else {
            throw new Exception("Invalid Seat Count");
        }
        if (screen > 0) {
            this.screen = screen;
        } else {
            throw new Exception("Invalid Screen Count");
        }
        this.name = name;
        this.location = location.toUpperCase();
    }

    Theater() {
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public Long getSeats() {
        return seats;
    }

    public void setSeats(Long seats) {
        this.seats = seats;
    }

    public Long getScreen() {
        return screen;
    }

    public String toString(){
        return "Theater(id="+ this.getId() + " location="+this.location+" name="+this.getName()+  ")";
    }
}
