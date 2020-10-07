package com.movie.ticketing.system.movieticketingsystem.controller;

import com.movie.ticketing.system.movieticketingsystem.DTO.PaymentStatus;
import com.movie.ticketing.system.movieticketingsystem.DTO.TicketStatus;
import com.movie.ticketing.system.movieticketingsystem.entities.Payment;
import com.movie.ticketing.system.movieticketingsystem.entities.Seats;
import com.movie.ticketing.system.movieticketingsystem.entities.Ticket;
import com.movie.ticketing.system.movieticketingsystem.repository.PaymentRepository;
import com.movie.ticketing.system.movieticketingsystem.repository.SeatsRepository;
import com.movie.ticketing.system.movieticketingsystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

import static com.movie.ticketing.system.movieticketingsystem.controller.SeatBookingController.seatLockingMap;

@RestController
public class PaymentController {
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    SeatsRepository seatsRepository;

    @PostMapping("/payment")
    Payment addPayment(@RequestParam("transactionId") String transactionId, @RequestParam("status") PaymentStatus status) {
        Payment payment = paymentRepository.findByTransactionId(transactionId);
        payment.setStatus(status);
        paymentRepository.save(payment);
        Ticket ticket = ticketRepository.findByTransactionId(payment.getTransactionId());

        if (status.equals(PaymentStatus.SUCCESS)) {
            ticket.setStatus(TicketStatus.SUCCESS);
            ticketRepository.save(ticket);
            for (Seats seats : ticket.getSeatsList()) {
                seats.setAvailable(false);
            }
            seatsRepository.saveAll(ticket.getSeatsList());
        } else if (status.equals(PaymentStatus.FAILED)) {
            ticket.setStatus(TicketStatus.FAILED);
            ticketRepository.save(ticket);
            for (Seats seats : ticket.getSeatsList()) {
                seats.setAvailable(true);
                Map<String, LocalDateTime> map = seatLockingMap.get(seats.getId());
                map.put("" + ticket.getDate() + ticket.getTime(), LocalDateTime.now().minusMinutes(10));
                seatLockingMap.put(seats.getId(), map);
            }
            seatsRepository.saveAll(ticket.getSeatsList());
        }
        return null;
    }
}
