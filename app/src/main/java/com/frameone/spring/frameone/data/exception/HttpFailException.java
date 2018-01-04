package com.frameone.spring.frameone.data.exception;

public class HttpFailException extends Exception {

    private String status;
    private String message;

    public HttpFailException(String message) {
        this(null, message);

    }

    public HttpFailException(String status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
