package com.movie.ticketing.system.movieticketingsystem.exception;

public class MovieTimeOverlappingException extends RuntimeException {

    String message;

    public MovieTimeOverlappingException(String message){
        this.message = message;
    }

}
