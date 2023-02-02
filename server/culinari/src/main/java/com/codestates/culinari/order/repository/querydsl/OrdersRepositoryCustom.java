package com.codestates.culinari.order.repository.querydsl;

import com.codestates.culinari.order.entitiy.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface OrdersRepositoryCustom {
    Page<Orders> findAllCreatedAfterAndProfile_Id(LocalDateTime createdAfterDateTime, Long profileId, Pageable pageable);

    Long countOrderByProfileId(LocalDateTime createdAfterDateTime, Long profileId);
}
