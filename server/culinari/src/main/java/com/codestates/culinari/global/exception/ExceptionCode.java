package com.codestates.culinari.global.exception;

import lombok.Getter;

public enum ExceptionCode {
    PAYMENT_AMOUNT_NOT_MATCHED(400, "Payment amount not matched"),
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    PRODUCT_NOT_FOUND(404,"Product not found"),
    USER_NOT_FOUND(404,"User not found"),
    PROFILE_NOT_FOUND(404,"Profile not found"),
    ORDER_NOT_FOUND(404,"Order not found"),
    ORDER_DETAIL_NOT_FOUND(404,"Order detail not found"),
    INQUIRY_NOT_FOUND(404,"Inquiry not found"),
    INQUIRYCOMMENT_NOT_FOUND(404,"InquiryComment not found"),
    DESTINATION_NOT_FOUND(404,"Destination not found"),
    REPRESENTATIVE_DESTINATION_NOT_FOUND(404,"Representative destination not found"),
    NOTICE_NOT_FOUND(404,"Notice not found"),
    CART_NOT_FOUND(404, "Cart not found"),
    PAYMENT_NOT_FOUND(404, "Payment not found"),
    USERNAME_EXISTS(405, "Username exist"),
    EMAIL_EXISTS(405, "Email exist"),
    PAYMENT_EXISTS(405, "Payment exists"),

    PRODUCT_LIKE_IS_EXIST(405,"Product Like is Exist"),
    TOSS_REQUEST_FAIL(500, "Toss request fail");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message){
        this.status = status;
        this.message = message;
    }
}
