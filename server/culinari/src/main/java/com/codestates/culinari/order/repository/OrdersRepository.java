package com.codestates.culinari.order.repository;

import com.codestates.culinari.order.entitiy.Orders;
import com.codestates.culinari.order.repository.querydsl.OrdersRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, String>, OrdersRepositoryCustom {
}
