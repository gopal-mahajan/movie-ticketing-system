package com.movie.ticketing.system.movieticketingsystem.repository;

import com.movie.ticketing.system.movieticketingsystem.entities.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {
    @Query(value = "select movie_id from screen where theater_id in ?1 ", nativeQuery = true)
    List<Long> getMovieByTheaterId(List<Long> theaterIds);

    @Query(value = "select * from screen where theater_id in (?1) and movie_id=?2", nativeQuery = true)
    List<Screen> getScreenByTheaterAndMovie(Set<Long> theaterIds, Long movieId);

    @Query(value = "select * from screen where theater_id=?1 and movie_id=?2", nativeQuery = true)
    Screen getScreenByTheaterAndMovie1(Long theaterId, Long movieId);

    @Query(value = "select * from screen where theater_id=?1 and id=?2",nativeQuery = true)
    Screen findByTheaterIdAndId(Long theaterId, Long screenId);
}
