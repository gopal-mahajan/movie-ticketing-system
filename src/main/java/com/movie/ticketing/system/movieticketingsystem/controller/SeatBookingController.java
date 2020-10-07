package com.movie.ticketing.system.movieticketingsystem.controller;

import com.movie.ticketing.system.movieticketingsystem.entities.Payment;
import com.movie.ticketing.system.movieticketingsystem.entities.Seats;
import com.movie.ticketing.system.movieticketingsystem.entities.Ticket;
import com.movie.ticketing.system.movieticketingsystem.repository.PaymentRepository;
import com.movie.ticketing.system.movieticketingsystem.repository.ScreenRepository;
import com.movie.ticketing.system.movieticketingsystem.repository.SeatsRepository;
import com.movie.ticketing.system.movieticketingsystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SeatBookingController {
    public static Map<Long, Map<String, LocalDateTime>> seatLockingMap = new HashMap<>();
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    ScreenRepository screenRepository;
    @Autowired
    SeatsRepository seatsRepository;
    @Autowired
    PaymentRepository paymentRepository;

    @PutMapping("/booking/{user_id}")
    String seatBoookingStatus(@PathVariable("user_id") Long userId, @RequestParam("ScreenId") Long screen_id, @RequestParam("Movie_Id") Long movie_id,
                              @RequestParam("Time") LocalTime time, @RequestParam("Date") String date,
                              @RequestParam("Seats") List<Long> seats) throws Exception {
        List<Seats> s = seatsRepository.seatStatus(screen_id, seats, time.toString(), LocalDate.parse(date));
        Map<String, LocalDateTime> innerMap = null;
        for (Seats i : s) {
            if (!i.isAvailable())
                throw new Exception("Seat not Available");
            if (seatLockingMap.containsKey(i.getId())) {
                innerMap = seatLockingMap.get(i.getId());
                LocalDateTime expiryTime = innerMap.get("" + date + time);
                if (expiryTime.isAfter(LocalDateTime.now())) {
                    throw new Exception("Seat not Available");
                }
                innerMap.put("" + date + time, LocalDateTime.now().plusMinutes(10));
            } else {
                innerMap = new HashMap<>();
                innerMap.put("" + date + time, LocalDateTime.now().plusMinutes(10));
                seatLockingMap.put(i.getId(), innerMap);
            }
        }
        Payment payment = new Payment(userId, 200 * s.size());
        payment = paymentRepository.save(payment);
        Ticket ticket = new Ticket(movie_id, s.get(0).getTheaterId(), time, LocalDate.parse(date), payment.getTransactionId(), s, userId);
        ticketRepository.save(ticket);
        return payment.getTransactionId();
    }
}
