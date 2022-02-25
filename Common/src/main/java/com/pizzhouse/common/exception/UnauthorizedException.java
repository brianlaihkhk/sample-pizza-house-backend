package com.pizzhouse.common.exception;

public class UnauthorizedException extends Exception { 
    public UnauthorizedException (String errorMessage) {
        super(errorMessage);
    }
}
