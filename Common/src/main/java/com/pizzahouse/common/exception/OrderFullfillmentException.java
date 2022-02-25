package com.pizzahouse.common.exception;

public class OrderFullfillmentException extends Exception { 
    public OrderFullfillmentException (String errorMessage) {
        super(errorMessage);
    }
}
