package com.pizzahouse.proxy.controller;

import com.pizzahouse.common.database.DatabaseTransaction;
import com.pizzahouse.common.entity.User;
import com.pizzahouse.common.model.ErrorDetail;
import com.pizzahouse.common.model.Order;
import com.pizzahouse.common.model.Response;
import com.pizzahouse.common.security.SecurityService;
import com.pizzhouse.common.exception.UnauthorizedException;

public class OrderService {
	DatabaseTransaction<Order> dbTransaction = new DatabaseTransaction<Order>();
	SecurityService securityService = new SecurityService();
	
	// Submit order data
	// Input : userId , session token, order information
	// Output : Success with order complete / fail with error message
	public Response<String> submitOrder (int id, String token, Order order) throws UnauthorizedException {
		Response<String> response = new Response<String>();

		
		
		return response;
	}
}
