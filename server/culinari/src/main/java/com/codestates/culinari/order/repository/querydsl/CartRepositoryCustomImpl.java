package com.codestates.culinari.order.repository.querydsl;

import com.codestates.culinari.global.exception.BusinessLogicException;
import com.codestates.culinari.global.exception.ExceptionCode;
import com.codestates.culinari.order.entitiy.Cart;
import com.codestates.culinari.order.entitiy.QCart;
import com.codestates.culinari.order.entitiy.QOrderDetail;
import com.codestates.culinari.order.entitiy.QOrders;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CartRepositoryCustomImpl extends QuerydslRepositorySupport implements CartRepositoryCustom {
    public CartRepositoryCustomImpl() { super(Cart.class); }

    @Override
    public void deleteAllByIdsAndProfile_Id(List<Long> cartIds, Long profileId) {
        QCart cart = QCart.cart;

        BooleanExpression condition = cart.id.in(cartIds)
                .and(cart.profile.id.eq(profileId));
        JPQLQuery<Cart> count = from(cart).where(condition);

        if (count.fetchCount() != cartIds.size()) {
            throw new BusinessLogicException(ExceptionCode.CART_NOT_FOUND);
        }

        delete(cart)
                .where(condition)
                .execute();
    }

    @Override
    public void deleteAllByOrderId(String orderId) {
        QCart cart = QCart.cart;
        QOrders order = QOrders.orders;
        QOrderDetail orderDetail = QOrderDetail.orderDetail;

        List<Long> cartIds = from(cart).select(cart.id)
                .where(cart.profile.id.eq(JPAExpressions.select(order.profile.id).from(order).where(order.id.eq(orderId)))
                        .and(cart.product.id.in(JPAExpressions.select(orderDetail.product.id).from(orderDetail).where(orderDetail.orders.id.eq(orderId))))
                ).fetch();

        delete(cart).where(cart.id.in(cartIds)).execute();
    }
}