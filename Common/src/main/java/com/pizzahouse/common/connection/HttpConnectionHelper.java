package com.pizzahouse.common.connection;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class HttpConnectionHelper<T> {
	public T post(String url, Object payload, Class<T> responseClass) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url);
		return target.request(MediaType.APPLICATION_JSON).accept(MediaType.TEXT_PLAIN_TYPE).post(Entity.json(payload), responseClass);
	}
}
