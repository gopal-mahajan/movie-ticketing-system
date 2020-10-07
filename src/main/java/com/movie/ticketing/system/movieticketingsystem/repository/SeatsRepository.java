package com.movie.ticketing.system.movieticketingsystem.repository;

import com.movie.ticketing.system.movieticketingsystem.entities.Screen;
import com.movie.ticketing.system.movieticketingsystem.entities.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SeatsRepository extends JpaRepository<Seats,Long> {
    @Query(value = "select * from seats where start_time=?2  and (booking_date=?3 or booking_date is null)and screen_id = ?1",nativeQuery = true)
    List<Seats> getSeatbyMovie(Screen screen, String time, LocalDate date);

    @Query(value ="select * from seats where screen_id=?1  and id in ?2 and time=?3 and date=?4",nativeQuery = true)
    List<Seats> seatStatus(Long screenId, List<Long> seatId, String time, LocalDate date);
}
