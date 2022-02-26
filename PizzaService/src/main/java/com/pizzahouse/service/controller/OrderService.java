package com.pizzahouse.service.controller;

import java.util.ArrayList;
import java.util.List;

import com.pizzahouse.service.entity.PizzaSizeFlatten;
import com.pizzahouse.service.entity.PizzaToppingFlatten;
import com.pizzahouse.service.initialization.DataLoader;
import com.pizzahouse.common.model.OrderConfirmation;
import com.pizzahouse.common.model.OrderConfirmationDetail;
import com.pizzahouse.service.model.Order;
import com.pizzahouse.service.model.OrderDetail;
import com.pizzahouse.common.model.Response;
import com.pizzahouse.common.exception.OrderFullfillmentException;
import com.pizzahouse.common.exception.UnauthorizedException;

public class OrderService {
	
	/**
	 * Order submittion process to persist Order data in DB 
	 * @param id User id from the user
	 * @param order The order supplied by the user, containing the ordered item from front end.
	 * @return Success with order complete / fail with error message 
	 */
	public Response<String> submitOrder (int id, Order order) throws UnauthorizedException, OrderFullfillmentException {
		Response<String> response = new Response<String>();
		
		OrderConfirmation orderConfirmation = finalizeOrder(order);
		
		
		
		return response;
	}
	
	
	/**
	 * Validation and calculation for the sub-total and grand total of the order 
	 * @param order The order supplied by the user, containing the ordered item from front end.
	 * @return Finalized confirmation object which is ready to persist in DB. The object containing the sub-total of each item, grand total of the order, and the item details of each order
	 */
	public OrderConfirmation finalizeOrder (Order order) throws UnauthorizedException, OrderFullfillmentException {

		OrderConfirmation orderConfirmation = new OrderConfirmation();
		List<OrderConfirmationDetail> orderConfirmationDetail = new ArrayList<OrderConfirmationDetail>();
	
		float totalAmount = 0.0f;
		
		if (order.getDetails() == null || order.getDetails().size() == 0) {
			throw new OrderFullfillmentException("There are no pizza ordered from the request");
		}

		for(OrderDetail orderDetail : order.getDetails()) {
			String sizeKey = orderDetail.getPizzaTypeId() + "," + orderDetail.getPizzaSizeId();
			if (DataLoader.pizzaSizeMap.containsKey(sizeKey)) {
				PizzaSizeFlatten pizzaSizeFlatten = DataLoader.pizzaSizeMap.get(sizeKey);
				float subTotal = orderDetail.getQuantity() * pizzaSizeFlatten.getPizzaSize().getPrice();
				
				OrderConfirmationDetail orderConfirmationItem = new OrderConfirmationDetail();
				orderConfirmationItem.setPizzaTypeId(pizzaSizeFlatten.getPizzaTypeId());
				orderConfirmationItem.setSubItemCategoryId(1);
				orderConfirmationItem.setSubItemId(pizzaSizeFlatten.getPizzaSizeId());
				orderConfirmationItem.setQuantity(orderDetail.getQuantity());
				orderConfirmationItem.setSinglePrice(pizzaSizeFlatten.getPizzaSize().getPrice());
				orderConfirmationItem.setSubTotal(subTotal);
				
				orderConfirmationDetail.add(orderConfirmationItem);
				totalAmount += subTotal;
			} else {
				throw new OrderFullfillmentException("The order request contains unsupported pizza size");
			}
		
			for(Integer toppingId : orderDetail.getPizzaToppingIdList()) {
				String toppingKey = orderDetail.getPizzaTypeId() + "," + toppingId;

				if (DataLoader.pizzaToppingMap.containsKey(toppingKey)) {
					PizzaToppingFlatten pizzaToppingFlatten = DataLoader.pizzaToppingMap.get(toppingKey);
					float subTotal = orderDetail.getQuantity() * pizzaToppingFlatten.getPizzaTopping().getPrice();
					
					OrderConfirmationDetail orderConfirmationItem = new OrderConfirmationDetail();
					orderConfirmationItem.setPizzaTypeId(pizzaToppingFlatten.getPizzaTypeId());
					orderConfirmationItem.setSubItemCategoryId(2);
					orderConfirmationItem.setSubItemId(pizzaToppingFlatten.getPizzaToppingId());
					orderConfirmationItem.setQuantity(orderDetail.getQuantity());
					orderConfirmationItem.setSinglePrice(pizzaToppingFlatten.getPizzaTopping().getPrice());
					orderConfirmationItem.setSubTotal(subTotal);
					
					orderConfirmationDetail.add(orderConfirmationItem);
					totalAmount += subTotal;
				} else {
					throw new OrderFullfillmentException("The order request contains unsupported pizza topping");
				}
			}
		}
		
		orderConfirmation.setTotalAmount(totalAmount);
		orderConfirmation.setDetails(orderConfirmationDetail);

		
		return orderConfirmation;
	}
}
