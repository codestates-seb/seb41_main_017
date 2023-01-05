package com.codestates.culinari.global.exception;

import lombok.Getter;

public enum ExceptionCode {

    ANSWER_NOT_FOUND(404,"Answer not found"),

    COMMENT_NOT_FOUND(404,"Comment not found"),

    QUESTION_NOT_FOUND(404,"Question Not Found"),

    QUESTION_EXISTS(409,"Question Exists"),

    QUESTION_CANNOT_CHANGE(403,"Question Can Not Be Changed"),

    Comment_EXISTS(409,"Comment Exists"),

    Comment_CANNOT_CHANGE(403,"Comment Can Not Be Changed"),

    ANSWER_EXISTS(409,"Answer Exists"),

    ANSWER_CANNOT_CHANGE(403,"Answer Can Not Be Changed"),

    ANSWER_DELETED(405,"Answer Already Deleted"),

    USER_NOT_FOUND(404,"User Not Found"),

    EMAIL_EXISTS(409,"Email Exists"),

    SAME_USERS(409, "same users"),

    NICKNAME_EXISTS(409,"Nickname Exists"),

    USER_NOT_ALLOWED(403,"User Not Allowed"),

    USER_CANNOT_CHANGE(403,"User Can Not Be Changed"),

    VOTED(409,"Already Voted"),

    UNAUTHORIZED(401,"Unauthorized");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message){
        this.status = status;
        this.message = message;
    }
}