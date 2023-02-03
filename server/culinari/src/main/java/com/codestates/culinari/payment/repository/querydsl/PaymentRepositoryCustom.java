package com.codestates.culinari.payment.repository.querydsl;

import com.codestates.culinari.payment.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface PaymentRepositoryCustom {
    Page<Payment> findAllCreatedAfterAndProfile_Id(LocalDateTime createdAfterDateTime, Long profileId, Pageable pageable);
}
