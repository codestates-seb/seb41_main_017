package com.codestates.culinari.order.Stub;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.order.constant.StatusType;
import com.codestates.culinari.order.dto.request.CartPatch;
import com.codestates.culinari.order.dto.request.CartPost;
import com.codestates.culinari.order.entitiy.Cart;
import com.codestates.culinari.order.entitiy.OrderDetail;
import com.codestates.culinari.order.entitiy.Orders;
import com.codestates.culinari.payment.constant.PayType;
import com.codestates.culinari.payment.dto.PaymentDto;
import com.codestates.culinari.payment.dto.request.PaymentRequest;
import com.codestates.culinari.payment.dto.response.PaymentFailResponse;
import com.codestates.culinari.payment.dto.response.PaymentInfoResponse;
import com.codestates.culinari.payment.dto.response.toss.PaymentTossDto;
import com.codestates.culinari.payment.entity.Payment;
import com.codestates.culinari.product.entitiy.CategoryDetail;
import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.user.constant.GenderType;
import com.codestates.culinari.user.entitiy.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.LongStream;

public class Stub {
    public static CartPost createCartPost(Long productId, Integer quantity) {
        return CartPost.of(productId, quantity);
    }

    public static CartPatch createCartPatch(Integer quantity) { return CartPatch.of(quantity); }

    public static Cart createCart(Long cartId, Integer quantity, Long profileId, Long productId) {
        Cart cart = Cart.of(quantity, createProfile(profileId), createProduct(productId));
        ReflectionTestUtils.setField(cart, "id", cartId);

        return cart;
    }

    /**
     * 빈껍데기 Page 생성
     */
    public static Page<Cart> createCartPage() {
        List<Cart> carts = LongStream.rangeClosed(1L, 5L)
                                .mapToObj(l -> createCart(l, 3, 1L, l))
                                .toList();
        Pageable pageable = PageRequest.of(0, 10);

        return new PageImpl<>(carts, pageable, carts.size());
    }

    public static Product createProduct(Long productId) {
        Product product = Product.of(
                CategoryDetail.of("세부 카테고리 명", "세부 카테고리 코드"),
                "상품 명",
                "상품 설명",
                BigDecimal.valueOf(1000L),
                "운송 방식",
                "브랜드",
                "판매자",
                "포장방식",
                "판매 단위",
                "중량",
                "원산지",
                "알레르기 정보"
        );
        ReflectionTestUtils.setField(product, "id", productId);

        return product;
    }

    public static CustomPrincipal createPrincipal(String username, Long userId, Long profileId) {
        return CustomPrincipal.of(username, userId, profileId);
    }

    public static Profile createProfile(Long profileId) {
        Profile profile = Profile.of(
                "사용자 명",
                "email@email.com",
                "010-0000-0000",
                BigDecimal.valueOf(0L),
                "주소",
                GenderType.MAN,
                LocalDate.now()
        );
        ReflectionTestUtils.setField(profile, "id", profileId);

        return profile;
    }

    public static Orders createOrder(Long orderId, Long profileId) {
        Orders order = Orders.of(
                "배송 주소",
                "수령자 명",
                "010-0000-0000",
                createProfile(profileId)
        );
        ReflectionTestUtils.setField(order, "id", orderId);

        return order;
    }

    public static Page<Orders> createOrderPage() {
        List<Orders> orders = LongStream.rangeClosed(1L, 5L)
                .mapToObj(l -> {
                    Orders order = createOrder(l, 1L);
                    order.getOrderDetails().addAll(LongStream.rangeClosed(1L, 3L).mapToObj(Stub::createOrderDetail).toList());
                    return order;
                })
                .toList();
        Pageable pageable = PageRequest.of(0, 10);

        return new PageImpl<>(orders, pageable, orders.size());
    }

    public static OrderDetail createOrderDetail(Long orderDetailId) {
        OrderDetail orderDetail = OrderDetail.of(
                3,
                StatusType.STAND_BY,
                createOrder(1L, 1L),
                createProduct(1L)
        );
        ReflectionTestUtils.setField(orderDetail, "id", orderDetailId);

        return orderDetail;
    }

    public static Payment createPayment(PayType payType, Integer amount, String productName) {
        return Payment.of(payType, BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP), productName, createOrder(1L, 1L), createProfile(1L));
    }

    public static Page<Payment> createPaymentPage() {
        List<Payment> payments = LongStream.rangeClosed(1L, 5L)
                .mapToObj(l -> createPayment(PayType.CARD, 1000, "상품명"))
                .toList();
        Pageable pageable = PageRequest.of(0, 10);

        return new PageImpl<>(payments, pageable, payments.size());
    }

    public static PaymentRequest createPaymentRequest() {
        return PaymentRequest.of(
                PayType.CARD,
                LongStream.rangeClosed(1L, 3L).boxed().toList(),
                "주소",
                "수령자 명",
                "010-0000-0000"
        );
    }

    public static PaymentTossDto createPaymentTossDto() {
        return new PaymentTossDto(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    public static PaymentFailResponse createPaymentFailResponse() {
        return PaymentFailResponse.of(
                "CODE",
                "에러 메세지",
                "0".repeat(18) + "1"
        );
    }

    public static PaymentInfoResponse createPaymentInfoResponse() {
        return PaymentInfoResponse.of(
                PayType.CARD,
                BigDecimal.valueOf(1000),
                "0".repeat(18) + "1",
                "상품명"
        );
    }
}
