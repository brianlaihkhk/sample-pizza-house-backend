package com.pizzahouse.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pizzahouse.service.config.Connection;

@Service
public class Connection {
	
    @Value("${pizzaServiceHost}")
    protected String pizzaServiceHost;
    
    @Value("${orderConfirmationServiceHost}")
    protected String orderConfirmationServiceHost;

    @Value("${serverJwtSecretKey}")
    protected String serverJwtSecretKey;
    
    @Value("${serverIssuerName}")
    protected String serverIssuerName;
    
    @Value("${jwtTtlMilliseconds}")
    protected long jwtTtlMilliseconds;

    @Value("${orderConfirmationServiceName}")
    protected String orderConfirmationServiceName;

	public String getPizzaServiceHost() {
		return pizzaServiceHost;
	}

	public void setPizzaServiceHost(String pizzaServiceHost) {
		this.pizzaServiceHost = pizzaServiceHost;
	}

	public String getOrderConfirmationServiceHost() {
		return orderConfirmationServiceHost;
	}

	public void setOrderConfirmationServiceHost(String orderConfirmationServiceHost) {
		this.orderConfirmationServiceHost = orderConfirmationServiceHost;
	}

	public String getServerJwtSecretKey() {
		return serverJwtSecretKey;
	}

	public void setServerJwtSecretKey(String serverJwtSecretKey) {
		this.serverJwtSecretKey = serverJwtSecretKey;
	}

	public String getServerIssuerName() {
		return serverIssuerName;
	}

	public void setServerIssuerName(String serverIssuerName) {
		this.serverIssuerName = serverIssuerName;
	}

	public long getJwtTtlMilliseconds() {
		return jwtTtlMilliseconds;
	}

	public void setJwtTtlMilliseconds(long jwtTtlMilliseconds) {
		this.jwtTtlMilliseconds = jwtTtlMilliseconds;
	}

	public String getOrderConfirmationServiceName() {
		return orderConfirmationServiceName;
	}

	public void setOrderConfirmationServiceName(String orderConfirmationServiceName) {
		this.orderConfirmationServiceName = orderConfirmationServiceName;
	}
}
