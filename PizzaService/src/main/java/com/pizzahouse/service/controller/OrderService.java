package com.pizzahouse.service.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pizzahouse.service.initialization.DataLoader;
import com.pizzahouse.common.model.Confirmation;
import com.pizzahouse.common.model.ConfirmationDetail;
import com.pizzahouse.service.model.FlattenPizzaSize;
import com.pizzahouse.service.model.FlattenPizzaTopping;
import com.pizzahouse.service.model.Order;
import com.pizzahouse.service.model.OrderDetail;
import com.pizzahouse.common.model.Response;
import com.pizzahouse.common.config.Connection;
import com.pizzahouse.common.config.Default;
import com.pizzahouse.common.connection.HttpConnectionHelper;
import com.pizzahouse.common.exception.OrderFullfillmentException;
import com.pizzahouse.common.exception.UnauthorizedException;

@Service
public class OrderService {
	@Autowired
	protected org.slf4j.Logger logger;
	/**
	 * Order submittion process to persist Order data in DB 
	 * @param id User id from the user
	 * @param order The order supplied by the user, containing the ordered item from front end.
	 * @return Success with order complete / fail with error message 
	 */
	public static Response submitOrder (int userId, Order order) throws UnauthorizedException, OrderFullfillmentException {
	
		Confirmation confirmation = finalizeOrder(order);
		confirmation.setUserId(userId);
		
		Response response = (new HttpConnectionHelper<Response>()).post(Connection.orderConfirmationServiceHost, confirmation, Response.class);
		
		return response;
	}
	
	
	/**
	 * Validation and calculation for the sub-total and grand total of the order 
	 * @param order The order supplied by the user, containing the ordered item from front end.
	 * @return Finalized confirmation object which is ready to persist in DB. The object containing the sub-total of each item, grand total of the order, and the item details of each order
	 */
	public static Confirmation finalizeOrder (Order order) throws UnauthorizedException, OrderFullfillmentException {

		Confirmation confirmation = new Confirmation();
		List<ConfirmationDetail> details = new ArrayList<ConfirmationDetail>();
	
		float totalAmount = 0.0f;
		
		if (order.getDetails() == null || order.getDetails().size() == 0) {
			throw new OrderFullfillmentException("There are no pizza ordered from the request");
		}

		for(OrderDetail orderDetail : order.getDetails()) {
			String sizeKey = orderDetail.getPizzaTypeId() + "," + orderDetail.getPizzaSizeId();
			if (DataLoader.pizzaSizeMap.containsKey(sizeKey)) {
				FlattenPizzaSize pizzaSizeFlatten = DataLoader.pizzaSizeMap.get(sizeKey);
				float subTotal = orderDetail.getQuantity() * pizzaSizeFlatten.getPizzaSize().getPrice();
				
				ConfirmationDetail confirmationDetail = new ConfirmationDetail();
				confirmationDetail.setPizzaTypeId(pizzaSizeFlatten.getPizzaTypeId());
				confirmationDetail.setSubItemCategoryId(Default.subItemCategoryIdPizzaSize);
				confirmationDetail.setSubItemReferenceId(pizzaSizeFlatten.getPizzaSizeId());
				confirmationDetail.setQuantity(orderDetail.getQuantity());
				confirmationDetail.setSinglePrice(pizzaSizeFlatten.getPizzaSize().getPrice());
				confirmationDetail.setSubTotal(subTotal);
				
				details.add(confirmationDetail);
				totalAmount += subTotal;
			} else {
				throw new OrderFullfillmentException("The order request contains unsupported pizza size");
			}
		
			for(Integer toppingId : orderDetail.getPizzaToppingIdList()) {
				String toppingKey = orderDetail.getPizzaTypeId() + "," + toppingId;

				if (DataLoader.pizzaToppingMap.containsKey(toppingKey)) {
					FlattenPizzaTopping pizzaToppingFlatten = DataLoader.pizzaToppingMap.get(toppingKey);
					float subTotal = orderDetail.getQuantity() * pizzaToppingFlatten.getPizzaTopping().getPrice();
					
					ConfirmationDetail confirmationDetail = new ConfirmationDetail();
					confirmationDetail.setPizzaTypeId(pizzaToppingFlatten.getPizzaTypeId());
					confirmationDetail.setSubItemCategoryId(Default.subItemCategoryIdPizzaTopping);
					confirmationDetail.setSubItemReferenceId(pizzaToppingFlatten.getPizzaToppingId());
					confirmationDetail.setQuantity(orderDetail.getQuantity());
					confirmationDetail.setSinglePrice(pizzaToppingFlatten.getPizzaTopping().getPrice());
					confirmationDetail.setSubTotal(subTotal);
					
					details.add(confirmationDetail);
					totalAmount += subTotal;
				} else {
					throw new OrderFullfillmentException("The order request contains unsupported pizza topping");
				}
			}
		}
		
		confirmation.setTotalAmount(totalAmount);
		confirmation.setDetails(details);

		
		return confirmation;
	}
}
