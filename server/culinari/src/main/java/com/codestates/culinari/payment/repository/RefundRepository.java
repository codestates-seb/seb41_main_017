package com.codestates.culinari.payment.repository;

import com.codestates.culinari.payment.entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundRepository extends JpaRepository<Refund, Long> {
}
