package com.n703.todoapi.services;

public class APIException extends RuntimeException {
    public APIException(String message) {
        super(message);
    }

}
