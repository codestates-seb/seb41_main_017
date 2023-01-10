package com.codestates.culinari.order.repository;

import com.codestates.culinari.order.entitiy.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
