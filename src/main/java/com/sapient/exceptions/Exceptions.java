package com.sapient.exceptions;

public enum Exceptions {

    WRONG_CREDENTIALS(1000, "Wrong credential!"),
    UN_AUTHORIZE(1001, "User is un-authorize to access this resource"),
    USER_ALREADY_EXIST(1002, "User Already exist"),
    DOCUMENT_NOT_FOUND(404, "Document Not Found");

    private int code;
    private String message;

    Exceptions(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
