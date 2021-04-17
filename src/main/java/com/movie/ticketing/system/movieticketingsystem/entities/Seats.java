package com.movie.ticketing.system.movieticketingsystem.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Seats")
public class Seats {
    private boolean isAvailable = true;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate bookingDate;
    private Long theaterId;
    private Long screenId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    private Long seatId;

    public Seats() {
    }

    public Seats(Long id,Long theaterId, Long screenId) throws Exception {
        this.seatId = id;
        if (theaterId > 0) {
            this.theaterId = theaterId;
        } else {
            throw new Exception("Theater doesn't exist");
        }

        if (screenId > 0) {
            this.screenId = screenId;
        } else {
            throw new Exception("Screen doesn't exist");
        }
    }

    public LocalTime getStarttime() {
        return startTime;
    }

    public void setStarttime(LocalTime starttime) {
        this.startTime = starttime;
    }

    public LocalTime getEndtime() {
        return endTime;
    }

    public void setEndtime(LocalTime endtime) {
        this.endTime = endtime;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Long getTheaterId() {
        return theaterId;
    }

    public Long getScreenId() {
        return screenId;
    }

    public Long getId() {
        return id;
    }

}
