package com.codestates.culinari.order.repository.querydsl;

import com.codestates.culinari.order.entitiy.Orders;
import com.codestates.culinari.order.entitiy.QOrderDetail;
import com.codestates.culinari.order.entitiy.QOrders;
import com.codestates.culinari.payment.entity.QPayment;
import com.codestates.culinari.payment.entity.QRefund;
import com.codestates.culinari.user.entitiy.QProfile;
import com.querydsl.jpa.JPAExpressions;
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
        QOrderDetail orderDetail = QOrderDetail.orderDetail;
        QPayment payment = QPayment.payment;
        QProfile profile = QProfile.profile;
        QRefund refund = QRefund.refund;

        JPQLQuery<Orders> query =
                from(order)
                        .innerJoin(order.profile, profile).fetchJoin()
                        .where(order.createdAt.gt(createdAfterDateTime)
                                .and(profile.id.eq(profileId))
                                .and(order.id.in(JPAExpressions.select(payment.order.id).from(payment).where(payment.paySuccessTf.eq(true)))))
                        .innerJoin(order.orderDetails, orderDetail).fetchJoin()
                        .where(orderDetail.id.notIn(JPAExpressions.select(refund.orderDetail.id).from(refund)))
                        .where(order.orderDetails.isNotEmpty());

        List<Orders> orders = getQuerydsl().applyPagination(pageable, query).fetch();

        return new PageImpl<>(orders, pageable, query.fetchCount());
    }

    @Override
    public Long countOrderByProfileId(LocalDateTime createdAfterDateTime, Long profileId) {
        QOrders order = QOrders.orders;
        QProfile profile = QProfile.profile;

        return from(order)
                .where(order.createdAt.gt(createdAfterDateTime)
                        .and(profile.id.eq(profileId))
                        .and(order.payment.paySuccessTf.eq(true)))
                .fetchCount();
    }
}
