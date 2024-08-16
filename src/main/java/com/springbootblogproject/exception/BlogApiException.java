package com.springbootblogproject.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public BlogApiException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public BlogApiException(Throwable cause, String message, HttpStatus status) {
        super(cause);
        this.message = message;
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
