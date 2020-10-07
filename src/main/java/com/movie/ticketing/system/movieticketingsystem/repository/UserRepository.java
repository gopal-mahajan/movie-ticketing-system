package com.movie.ticketing.system.movieticketingsystem.repository;

import com.movie.ticketing.system.movieticketingsystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
