package com.movie.ticketing.system.movieticketingsystem.entities;


import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movies {
    private String name;
    private String cast;
    private Integer year;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String summary;
    private Integer rating;
    private Integer movieLength;

    Movies() {
    }

    public Movies(String name, String cast, String summary, int rating, int year, int movieLength) {
        this.cast = cast;
        this.name = name;
        this.rating = rating;
        this.summary = summary;
        this.year = year;
        this.movieLength = movieLength;
    }

    public Integer getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(Integer movieLength) {
        this.movieLength = movieLength;
    }

    public String getName() {
        return name;
    }

    public String getCast() {
        return cast;
    }

    public String getSummary() {
        return summary;
    }


    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getYear() {
        return year;
    }
}
