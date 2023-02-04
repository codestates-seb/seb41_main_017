package com.codestates.culinari.order.repository.querydsl;

import com.codestates.culinari.order.entitiy.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderDetailRepositoryCustom {
    List<OrderDetail> findAllPaidByIdAndProfileId(List<Long> orderDetailIds, Long profileId);

    Page<OrderDetail> findAllCreatedAfterAndProfile_Id(LocalDateTime createdAfterDateTime, Long profileId, Pageable pageable);

    Long countOnShippingByProfileId(LocalDateTime createdAfterDateTime, Long profileId);

    String findPaymentKey(Long orderId);
}
