package com.movie.ticketing.system.movieticketingsystem.service;

import com.movie.ticketing.system.movieticketingsystem.entities.Payment;
import com.movie.ticketing.system.movieticketingsystem.entities.Seats;
import com.movie.ticketing.system.movieticketingsystem.entities.Ticket;
import com.movie.ticketing.system.movieticketingsystem.repository.PaymentRepository;
import com.movie.ticketing.system.movieticketingsystem.repository.ScreenRepository;
import com.movie.ticketing.system.movieticketingsystem.repository.SeatsRepository;
import com.movie.ticketing.system.movieticketingsystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SeatBookingService {
    public static Map<Long, Map<String, LocalDateTime>> seatLockingMap = new HashMap<>();
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    ScreenRepository screenRepository;
    @Autowired
    SeatsRepository seatsRepository;
    @Autowired
    PaymentRepository paymentRepository;

    public String bookSeat(Long screenId, List<Long> seatIds, LocalTime time, LocalDate date, Long userId, Long movieId)
            throws Exception {
        List<Seats> s = seatsRepository.seatStatus(screenId, seatIds, time, date);
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
        Ticket ticket = new Ticket(movieId, s.get(0).getTheaterId(), time, date, payment.getTransactionId(), s, userId);
        ticketRepository.save(ticket);
        return payment.getTransactionId();
    }
}
