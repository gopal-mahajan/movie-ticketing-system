package com.movie.ticketing.system.movieticketingsystem.entities;

import com.movie.ticketing.system.movieticketingsystem.DTO.TicketStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long movieId;
    private Long theaterId;
    private LocalTime time;
    private Long userId;
    private LocalDate date;
    @OneToMany
    private List<Seats> seatsList;
    private String transactionId;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    Ticket() {
    }

    public Ticket(Long movieId, Long theaterId, LocalTime time, LocalDate date, String transactionId, List<Seats> seats, Long userId) {

        this.movieId = movieId;
        this.theaterId = theaterId;
        this.time = time;
        this.transactionId = transactionId;
        this.seatsList = seats;
        this.status = TicketStatus.PENDING;
        this.userId = userId;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public Long getTheaterId() {
        return theaterId;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public List<Seats> getSeatsList() {
        return seatsList;
    }

    public void setSeatsList(List<Seats> seatsList) {
        this.seatsList = seatsList;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }
}
