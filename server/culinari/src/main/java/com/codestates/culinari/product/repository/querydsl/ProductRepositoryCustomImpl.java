package com.codestates.culinari.product.repository.querydsl;

import com.codestates.culinari.order.entitiy.QOrderDetail;
import com.codestates.culinari.payment.entity.QPayment;
import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.product.entitiy.QProduct;
import com.codestates.culinari.user.entitiy.QProfile;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;

import static com.codestates.culinari.product.entitiy.QProduct.product;

@Slf4j
public class ProductRepositoryCustomImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {

    public ProductRepositoryCustomImpl() {
        super(Product.class);
    }

    @Override
    public Page<Product> findAllWithSortAndFilter(List<String> category, List<String> brand, Pageable pageable) throws UnsupportedEncodingException {
        QProduct product = QProduct.product;

        List<Product> products =
                from(product)
                        .select(product)
                        .where(eqCategoryCode(category),(eqBrand(brand)))
                        .orderBy(productSort(pageable))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch();
        Long count =
                from(product)
                        .select(product.count())
                        .where(eqCategoryCode(category),(eqBrand(brand)))
                        .fetchOne();

        return new PageImpl<>(products,pageable,count);
    }

    private BooleanExpression eqCategoryCode(List<String> category){
        return category != null ? product.categoryDetail.category.categoryCode.in(category) : null;
    }
    private BooleanExpression eqBrand(List<String> brand){
        return brand != null ? product.brand.in(brand) : null;
    }

    private OrderSpecifier<?> productSort(Pageable pageable) {
        if (!pageable.getSort().isEmpty()) {
            for (Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

                switch (order.getProperty()) {
                    case "id":
                        return new OrderSpecifier<>(direction, product.createdAt);

                    case "price":
                        return new OrderSpecifier<>(direction, product.price);
                }
            }
        }
        return null;
    }

    @Override
    public Page<Product> findAllFrequentOrderProduct(LocalDateTime createdAfterDateTime, Integer frequency, Long profileId, Pageable pageable) {
        QOrderDetail orderDetail = QOrderDetail.orderDetail;
        QProduct product = QProduct.product;
        QPayment payment = QPayment.payment;
        QProfile profile = QProfile.profile;

        JPQLQuery<Product> query =
                from(orderDetail)
                        .select(product)
                        .innerJoin(orderDetail.product, product)
                        .where(orderDetail.createdAt.goe(createdAfterDateTime)
                                .and(product.id.eq(orderDetail.product.id))
                                .and(orderDetail.orders.id.in(
                                                JPAExpressions.select(payment.order.id).from(payment)
                                                        .where(
                                                                payment.paySuccessTf.eq(true)
                                                                        .and(payment.profile.id.eq(profileId))
                                                        ))))
//                        .fetchJoin()
                        .innerJoin(orderDetail.orders.profile, profile)
                        .where(profile.id.eq(profileId))
//                        .fetchJoin()
                        .groupBy(product.id)
                        .having(product.id.count().goe(frequency));
        List<Product> products = getQuerydsl().applyPagination(pageable, query).fetch();

        return new PageImpl<>(products, pageable, query.fetchCount());
    }

    @Override
    public Page<Product> findBestProducts(List<String> category, List<String> brand, Integer frequency, Pageable pageable) {
        QOrderDetail orderDetail = QOrderDetail.orderDetail;
        QProduct product = QProduct.product;
        QPayment payment = QPayment.payment;

        JPQLQuery<Product> query =
                from(orderDetail)
                        .select(product)
                        .innerJoin(orderDetail.product, product)
                        .where(orderDetail.product.id.eq(product.id),(orderDetail.orders.id.in(
                                        JPAExpressions.select(payment.order.id).from(payment)
                                                .where(
                                                        payment.paySuccessTf.eq(true)
                                                ))))
//                        .where(orderDetail.product.goe(createdAfterDateTime)
//                                .and(product.id.eq(orderDetail.product.id))
//                                .and(orderDetail.orders.id.in(
//                                        JPAExpressions.select(payment.order.id).from(payment)
//                                                .where(
//                                                        payment.paySuccessTf.eq(true)
//                                                ))))
//                        .fetchJoin()
//                        .innerJoin(orderDetail.product ,product)
                        .where(eqCategoryCode(category),(eqBrand(brand)))
//                        .fetchJoin()
                        .groupBy(product.id)
                        .having(product.id.count().goe(frequency));
        List<Product> products = getQuerydsl().applyPagination(pageable, query).fetch();

        return new PageImpl<>(products, pageable, query.fetchCount());
    }

    @Override
    public Long countFrequentOrderProduct(LocalDateTime createdAfterDateTime, Integer frequency, Long profileId) {
        QOrderDetail orderDetail = QOrderDetail.orderDetail;

        return from(orderDetail)
                .select(orderDetail.product.id)
                .where(orderDetail.createdAt.goe(createdAfterDateTime)
                        .and(orderDetail.product.id.eq(orderDetail.product.id))
                        .and(orderDetail.orders.payment.paySuccessTf.eq(true))
                        .and(orderDetail.orders.profile.id.eq(profileId)))
                .groupBy(orderDetail.product.id)
                .having(orderDetail.product.id.count().goe(frequency))
                .fetchCount();
    }
}
