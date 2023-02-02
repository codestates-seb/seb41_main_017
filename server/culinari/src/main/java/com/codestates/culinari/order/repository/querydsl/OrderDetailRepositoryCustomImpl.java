package com.codestates.culinari.order.repository.querydsl;

import com.codestates.culinari.global.exception.BusinessLogicException;
import com.codestates.culinari.global.exception.ExceptionCode;
import com.codestates.culinari.order.constant.StatusType;
import com.codestates.culinari.order.entitiy.OrderDetail;
import com.codestates.culinari.order.entitiy.QOrderDetail;
import com.codestates.culinari.payment.entity.QPayment;
import com.codestates.culinari.payment.entity.QRefund;
import com.codestates.culinari.product.entitiy.QProduct;
import com.codestates.culinari.product.entitiy.QProductImage;
import com.codestates.culinari.user.entitiy.QProfile;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDetailRepositoryCustomImpl extends QuerydslRepositorySupport implements OrderDetailRepositoryCustom {
    public OrderDetailRepositoryCustomImpl() {
        super(OrderDetail.class);
    }

    @Override
    public List<OrderDetail> findAllPaidByIdAndProfileId(List<Long> orderDetailIds, Long profileId) {
        QOrderDetail orderDetail = QOrderDetail.orderDetail;
        QRefund refund = QRefund.refund;

        List<OrderDetail> orderDetails =
                from(orderDetail)
                        .where(orderDetail.orders.payment.paySuccessTf.eq(true)
                                .and(orderDetail.orders.profile.id.eq(profileId))
                                .and(orderDetail.id.in(orderDetailIds))
                                .and(orderDetail.id.notIn(JPAExpressions.select(refund.orderDetail.id).from(refund)))
                        )
                        .fetch();

        if (orderDetails.size() != orderDetailIds.size()) throw new BusinessLogicException(ExceptionCode.ORDER_DETAIL_NOT_FOUND);

        return orderDetails;
    }

    @Override
    public Page<OrderDetail> findAllCreatedAfterAndProfile_Id(LocalDateTime createdAfterDateTime, Long profileId, Pageable pageable) {
        QOrderDetail orderDetail = QOrderDetail.orderDetail;
        QProduct product = QProduct.product;
        QProductImage productImage = QProductImage.productImage;
        QRefund refund = QRefund.refund;

        JPQLQuery<OrderDetail> query =
                from(orderDetail)
                        .innerJoin(orderDetail.product, product).fetchJoin()
                        .innerJoin(product.productImages, productImage).fetchJoin()
                        .where(orderDetail.createdAt.gt(createdAfterDateTime)
                                .and(orderDetail.orders.profile.id.eq(profileId))
                                .and(orderDetail.orders.payment.paySuccessTf.eq(true))
                                .and(orderDetail.id.notIn(JPAExpressions.select(refund.orderDetail.id).from(refund))));
        List<OrderDetail> orderDetails = getQuerydsl().applyPagination(pageable, query).fetch();

        return new PageImpl<>(orderDetails, pageable, query.fetchCount());
    }

    @Override
    public Long countOnShippingByProfileId(LocalDateTime createdAfterDateTime, Long profileId) {
        QOrderDetail orderDetail = QOrderDetail.orderDetail;
        QRefund refund = QRefund.refund;

        return from(orderDetail)
                .where(orderDetail.createdAt.gt(createdAfterDateTime)
                        .and(orderDetail.orders.profile.id.eq(profileId))
                        .and(orderDetail.orders.payment.paySuccessTf.eq(true))
                        .and(orderDetail.id.notIn(JPAExpressions.select(refund.orderDetail.id).from(refund)))
                        .and(orderDetail.statusType.eq(StatusType.ON_SHIPPING)))
                .fetchCount();
    }

    @Override
    public String findPaymentKey(Long orderId) {
        QOrderDetail orderDetail = QOrderDetail.orderDetail;

        return from(orderDetail)
                .select(orderDetail.orders.payment.paymentKey)
                .where(orderDetail.id.eq(orderId))
                .fetchOne();
    }
}
