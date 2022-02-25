package com.pizzahouse.common.exception;

public class UnauthorizedException extends Exception { 
    public UnauthorizedException (String errorMessage) {
        super(errorMessage);
    }
}
