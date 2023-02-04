package com.codestates.culinari.order.repository;

import com.codestates.culinari.order.entitiy.OrderDetail;
import com.codestates.culinari.order.repository.querydsl.OrderDetailRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>, OrderDetailRepositoryCustom {

    OrderDetail findByIdAndOrders_Profile_Id(Long orderDetailId, Long profileId);

    Page<OrderDetail> findAllByOrdersProfileIdAndProductReviewIsNull(Long profileId, Pageable pageable);

    OrderDetail findByProductIdAndProductReviewIdAndProductReviewIsNotNull(Long productId,Long productReviewId);
}
