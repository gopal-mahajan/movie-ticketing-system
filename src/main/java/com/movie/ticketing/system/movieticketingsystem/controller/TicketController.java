package com.movie.ticketing.system.movieticketingsystem.controller;

import com.movie.ticketing.system.movieticketingsystem.entities.Ticket;
import com.movie.ticketing.system.movieticketingsystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TicketController {

    @Autowired
    TicketRepository ticketRepository;

    @GetMapping("/ticket/{user_id}")
    List<Ticket> getActiveTickets(@PathVariable("user_id") Long userId) {
        List<Ticket> tickets = ticketRepository.findByUserId(userId, LocalDate.now());
        return tickets;
    }
}
