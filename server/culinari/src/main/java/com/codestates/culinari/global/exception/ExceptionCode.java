package com.codestates.culinari.global.exception;

import lombok.Getter;

public enum ExceptionCode {

    UNAUTHORIZED(401, "UNAUTHORIZED"),
    PRODUCT_NOT_FOUND(404,"Product not found"),
    USER_NOT_FOUND(404,"User not found"),
    PROFILE_NOT_FOUND(404,"Profile not found"),
    ORDER_NOT_FOUND(404,"Order not found"),
    CART_NOT_FOUND(404, "Cart not found"),
    USERNAME_EXISTS(405, "Username exist"),
    EMAIL_EXISTS(405, "Username exist");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message){
        this.status = status;
        this.message = message;
    }
}
