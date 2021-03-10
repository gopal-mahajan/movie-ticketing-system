package com.movie.ticketing.system.movieticketingsystem.repository;

import com.movie.ticketing.system.movieticketingsystem.entities.Screen;
import com.movie.ticketing.system.movieticketingsystem.entities.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SeatsRepository extends JpaRepository<Seats, Long> {
    @Query(value = "select * from seats where start_time=?2  and (booking_date=?3 or booking_date is null) and screen_id = ?1", nativeQuery = true)
    List<Seats> getSeatbyMovie(Long screen, LocalTime time, LocalDate date);

    @Query(value = "select * from seats where screen_id=?1  and seat_id in ?2 and start_time=?3 and (booking_date=?4 or booking_date is null)", nativeQuery = true)
    List<Seats> seatStatus(Long screenId, List<Long> seatId, LocalTime time, LocalDate date);
}
