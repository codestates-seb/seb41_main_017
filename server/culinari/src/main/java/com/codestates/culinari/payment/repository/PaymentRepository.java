package com.codestates.culinari.payment.repository;

import com.codestates.culinari.payment.entity.Payment;
import com.codestates.culinari.payment.repository.querydsl.PaymentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long>, PaymentRepositoryCustom {
    Optional<Payment> findByOrder_id(String orderId);
}
