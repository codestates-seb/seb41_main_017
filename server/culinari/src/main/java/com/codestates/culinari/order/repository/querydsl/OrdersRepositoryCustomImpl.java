package com.codestates.culinari.order.repository.querydsl;

import com.codestates.culinari.order.entitiy.Orders;
import com.codestates.culinari.order.entitiy.QOrders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.util.List;
public class OrdersRepositoryCustomImpl extends QuerydslRepositorySupport implements OrdersRepositoryCustom {

    public OrdersRepositoryCustomImpl() {
        super(Orders.class);
    }

    @Override
    public Page<Orders> findAllCreatedAfterAndProfile_Id(LocalDateTime createdAfterDateTime, Long profileId, Pageable pageable) {
        QOrders order = QOrders.orders;

        List<Orders> orders =
                from(order)
                        .select(order)
                        .where(order.createdAt.gt(createdAfterDateTime)
                                .and(order.profile.id.eq(profileId)))
                        .orderBy(order.createdAt.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch();

        Long count =
                from(order)
                        .select(order.count())
                        .where(order.createdAt.gt(createdAfterDateTime)
                                .and(order.profile.id.eq(profileId)))
                        .fetchOne();

        return new PageImpl<>(orders, pageable, count);
    }
}
