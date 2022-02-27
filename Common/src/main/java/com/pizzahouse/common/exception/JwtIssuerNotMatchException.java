package com.pizzahouse.common.exception;

public class JwtIssuerNotMatchException extends Exception { 
    public JwtIssuerNotMatchException (String errorMessage) {
        super(errorMessage);
    }
}
