package com.movie.ticketing.system.movieticketingsystem.repository;

import com.movie.ticketing.system.movieticketingsystem.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByTransactionId(String txnId);
}
