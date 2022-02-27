package com.pizzahouse.common.exception;

public class JwtMessageExpiredException extends Exception { 
    public JwtMessageExpiredException (String errorMessage) {
        super(errorMessage);
    }
}
