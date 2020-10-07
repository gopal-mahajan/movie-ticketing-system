package com.movie.ticketing.system.movieticketingsystem.repository;

import com.movie.ticketing.system.movieticketingsystem.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findByTransactionId(String txnId);

    @Query(value = "Select * from ticket where user_id = ?1 and date >= ?2 ", nativeQuery = true)
    List<Ticket> findByUserId(Long userId, LocalDate todayDate);
}
