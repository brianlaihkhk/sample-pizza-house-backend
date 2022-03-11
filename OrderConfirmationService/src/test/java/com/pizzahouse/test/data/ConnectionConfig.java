package com.pizzahouse.test.data;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.pizzahouse.order.config.Connection;

@TestConfiguration
public class ConnectionConfig {

    @Bean
    public Connection generateConnection() {
    	
    	Connection connection = new Connection();
    	connection.setPizzaServiceHost("http://localhost:8080/PizzaService/");
    	connection.setOrderConfirmationServiceHost("http://localhost:8080/PizzaService/");
    	connection.setOrderConfirmationServiceName("confirm");
    	connection.setServerIssuerName("a2aac795-094f-4bb4-83a7-66a5536ae81d");
    	connection.setServerJwtSecretKey("948d939f-12bf-441b-aa0c-8a1aca9cd348");
    	connection.setJwtTtlMilliseconds(10000);

        return connection;
    }
}
