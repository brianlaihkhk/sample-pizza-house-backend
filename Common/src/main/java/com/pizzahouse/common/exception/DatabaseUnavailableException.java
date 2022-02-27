package com.pizzahouse.common.exception;

public class DatabaseUnavailableException extends Exception { 
    public DatabaseUnavailableException (String errorMessage) {
        super(errorMessage);
    }
}
