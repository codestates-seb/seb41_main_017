package com.codestates.culinari.payment.repository;

import com.codestates.culinari.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByOrder_id(Long orderId);
}
