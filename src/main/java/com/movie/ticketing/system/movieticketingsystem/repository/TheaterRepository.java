package com.movie.ticketing.system.movieticketingsystem.repository;

import com.movie.ticketing.system.movieticketingsystem.entities.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface TheaterRepository extends JpaRepository<Theater,Long> {

    @Query(value = "select distinct location from theater",nativeQuery = true)
    List<String> findDistinctLocation();

    List<Theater> findByLocation(String location);

}
