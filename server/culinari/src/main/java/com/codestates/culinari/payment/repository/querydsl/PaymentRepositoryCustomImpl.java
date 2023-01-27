package com.codestates.culinari.payment.repository.querydsl;

import com.codestates.culinari.order.entitiy.QOrders;
import com.codestates.culinari.payment.entity.Payment;
import com.codestates.culinari.payment.entity.QPayment;
import com.codestates.culinari.user.entitiy.QProfile;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.util.List;

public class PaymentRepositoryCustomImpl extends QuerydslRepositorySupport implements PaymentRepositoryCustom{

    public PaymentRepositoryCustomImpl() {
        super(Payment.class);
    }

    @Override
    public Page<Payment> findAllCreatedAfterAndProfile_Id(LocalDateTime createdAfterDateTime, Long profileId, Pageable pageable) {
        QPayment payment = QPayment.payment;
        QProfile profile = QProfile.profile;
        QOrders order = QOrders.orders;

        JPQLQuery<Payment> query =
                from(payment)
                        .innerJoin(payment.profile, profile)
                        .innerJoin(payment.order, order).fetchJoin()
                        .where(order.createdAt.gt(createdAfterDateTime)
                                .and(payment.paySuccessTf.eq(true))
                                .and(profile.id.eq(profileId)))
                        .orderBy(order.createdAt.desc());
        List<Payment> payments = getQuerydsl().applyPagination(pageable, query).fetch();

        return new PageImpl<>(payments, pageable, query.fetchCount());
    }
}
