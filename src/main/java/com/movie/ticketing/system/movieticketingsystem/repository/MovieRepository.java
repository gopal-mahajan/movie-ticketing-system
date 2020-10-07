package com.movie.ticketing.system.movieticketingsystem.repository;

import com.movie.ticketing.system.movieticketingsystem.entities.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movies, Long> {

    // Table is created : now write functions to fetch or update details in the table Movies.
    @Query(value = "select * from movies where id in ?1 ",nativeQuery = true)
    List<Movies> getMoviebyId(List<Long> movieId);

}
