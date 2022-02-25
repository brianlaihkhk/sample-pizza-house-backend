package com.pizzahouse.service.controller;

import java.util.ArrayList;
import java.util.List;

import com.pizzahouse.common.entity.User;
import com.pizzahouse.service.entity.Pizza;
import com.pizzahouse.service.entity.PizzaSizeFlatten;
import com.pizzahouse.service.entity.PizzaToppingFlatten;
import com.pizzahouse.service.initialization.DataLoader;
import com.pizzahouse.common.model.ErrorDetail;
import com.pizzahouse.common.model.OrderConfirmation;
import com.pizzahouse.common.model.OrderConfirmationDetail;
import com.pizzahouse.service.model.Order;
import com.pizzahouse.service.model.OrderDetail;
import com.pizzahouse.common.model.Response;
import com.pizzahouse.common.security.SecurityService;
import com.pizzahouse.common.exception.OrderFullfillmentException;
import com.pizzahouse.common.exception.UnauthorizedException;

public class OrderService {
	private SecurityService securityService = new SecurityService();
	
	// Submit order data
	// Input : userId , session token, order information
	// Output : Success with order complete / fail with error message
	public Response<String> submitOrder (int id, String token, Order order) throws UnauthorizedException, OrderFullfillmentException {
		Response<String> response = new Response<String>();
		OrderConfirmation orderConfirmation = new OrderConfirmation();
		List<OrderConfirmationDetail> orderConfirmationDetail = new ArrayList<OrderConfirmationDetail>();
		float totalAmount = 0.0f;
		int recordCount = 0;
		
		if (order.getDetails().size() == 0) {
			throw new OrderFullfillmentException("There are no pizza ordered from the request");
		}
		
		// Calculate the total amount (Suppose there are no total amount to submit from front-end)
		// O(n^3) problem, performance may impact if there are many pizza ordering in one order
		for(OrderDetail orderDetail : order.getDetails()) {
			recordCount += 1;
			for (PizzaSizeFlatten pizzaSizeFlatten : DataLoader.pizzaSizeFlatten) {
				if (orderDetail.getPizzaSizeId() == pizzaSizeFlatten.getPizzaSizeId() && orderDetail.getPizzaTypeId() == pizzaSizeFlatten.getPizzaTypeId()) {
					float subTotal = orderDetail.getQuantity() * pizzaSizeFlatten.getPizzaSize().getPrice();
					
					OrderConfirmationDetail orderConfirmationItem = new OrderConfirmationDetail();
					orderConfirmationItem.setItemDescription(pizzaSizeFlatten.getPizzaSize().getDescription());
					orderConfirmationItem.setItemName(pizzaSizeFlatten.getPizzaSize().getName());
					orderConfirmationItem.setQuantity(orderDetail.getQuantity());
					orderConfirmationItem.setSinglePrice(pizzaSizeFlatten.getPizzaSize().getPrice());
					orderConfirmationItem.setSubTotal(subTotal);
					
					orderConfirmationDetail.add(orderConfirmationItem);
					totalAmount += subTotal;
				}
			}
			
			for(Integer toppingId : orderDetail.getPizzaToppingId()) {
				recordCount += 1;
				for (PizzaToppingFlatten pizzaToppingFlatten : DataLoader.pizzaToppingFlatten) {
					if (orderDetail.getPizzaSizeId() == pizzaToppingFlatten.getPizzaToppingId() && toppingId == pizzaToppingFlatten.getPizzaToppingId()) {
						float subTotal = orderDetail.getQuantity() * pizzaToppingFlatten.getPizzaTopping().getPrice();
						
						OrderConfirmationDetail orderConfirmationItem = new OrderConfirmationDetail();
						orderConfirmationItem.setItemDescription(pizzaToppingFlatten.getPizzaTopping().getDescription());
						orderConfirmationItem.setItemName(pizzaToppingFlatten.getPizzaTopping().getName());
						orderConfirmationItem.setQuantity(orderDetail.getQuantity());
						orderConfirmationItem.setSinglePrice(pizzaToppingFlatten.getPizzaTopping().getPrice());
						orderConfirmationItem.setSubTotal(subTotal);
						
						orderConfirmationDetail.add(orderConfirmationItem);
						totalAmount += subTotal;
						
					}
				}
			}
		}
		
		if (recordCount != orderConfirmation.getDetails().size()) {
			throw new OrderFullfillmentException("The order request contains unsupported pizza toppings or size");
		}
		orderConfirmation.setTotalAmount(totalAmount);
		orderConfirmation.setDetails(orderConfirmationDetail);

		
		return response;
	}
}
