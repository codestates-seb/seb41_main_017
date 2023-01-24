package com.codestates.culinari.order.repository.querydsl;

import com.codestates.culinari.order.entitiy.Orders;
import com.codestates.culinari.order.entitiy.QOrders;
import com.codestates.culinari.user.entitiy.QProfile;
import com.querydsl.jpa.JPQLQuery;
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
        QProfile profile = QProfile.profile;

        JPQLQuery<Orders> query =
                from(order)
                        .innerJoin(order.profile, profile).fetchJoin()
                        .where(order.createdAt.gt(createdAfterDateTime)
                                .and(profile.id.eq(profileId)))
                        .orderBy(order.createdAt.desc());
        List<Orders> orders = getQuerydsl().applyPagination(pageable, query).fetch();

        return new PageImpl<>(orders, pageable, query.fetchCount());
    }
}
