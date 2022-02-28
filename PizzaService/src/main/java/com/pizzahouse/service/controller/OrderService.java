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
import com.pizzahouse.service.security.JwtService;
import com.pizzahouse.common.model.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzahouse.common.config.Connection;
import com.pizzahouse.common.config.Default;
import com.pizzahouse.common.connection.HttpConnectionHelper;
import com.pizzahouse.common.exception.DatabaseUnavailableException;
import com.pizzahouse.common.exception.JwtIssuerNotMatchException;
import com.pizzahouse.common.exception.JwtMessageExpiredException;
import com.pizzahouse.common.exception.OrderFullfillmentException;
import com.pizzahouse.common.exception.UnauthorizedException;

@Service
public class OrderService {
	private static ObjectMapper mapper = new ObjectMapper();

	@Autowired
	protected org.slf4j.Logger logger;
	@Autowired
	protected JwtService jwtService;
	@Autowired
	protected DataLoader dataLoader;
	
	/**
	 * Order submittion process to persist Order data in DB 
	 * @param id User id from the user
	 * @param order The order supplied by the user, containing the ordered item from front end.
	 * @return Success with order complete / fail with error message 
	 * @throws JsonProcessingException 
	 * @throws DatabaseUnavailableException 
	 * @throws JwtIssuerNotMatchException 
	 * @throws JwtMessageExpiredException 
	 */
	public Response<String> submitOrder (int userId, Order order) throws UnauthorizedException, OrderFullfillmentException, JsonProcessingException, DatabaseUnavailableException, JwtMessageExpiredException, JwtIssuerNotMatchException {
	
		Confirmation confirmation = finalizeOrder(order);
		confirmation.setUserId(userId);
		
		String jwtMessage = jwtService.createJwt(String.valueOf(userId), Connection.serverIssuerName, mapper.writeValueAsString(confirmation), Connection.jwtTtlMilliseconds, Connection.serverJwtSecretKey);
		
		String jwtResponse = (new HttpConnectionHelper<String>()).post(Connection.orderConfirmationServiceHost + Connection.orderConfirmationServiceName, jwtMessage, String.class);
		
		Response<String> response = (Response<String>) jwtService.decodeMessage(Response.class, jwtMessage);
		return response;
	}
	
	
	/**
	 * Validation and calculation for the sub-total and grand total of the order 
	 * @param order The order supplied by the user, containing the ordered item from front end.
	 * @return Finalized confirmation object which is ready to persist in DB. The object containing the sub-total of each item, grand total of the order, and the item details of each order
	 * @throws DatabaseUnavailableException 
	 */
	public Confirmation finalizeOrder (Order order) throws UnauthorizedException, OrderFullfillmentException, DatabaseUnavailableException {

		Confirmation confirmation = new Confirmation();
		List<ConfirmationDetail> details = new ArrayList<ConfirmationDetail>();
	
		float totalAmount = 0.0f;
		
		if (order.getDetails() == null || order.getDetails().size() == 0) {
			throw new OrderFullfillmentException("There are no pizza ordered from the request");
		}
		
		if (DataLoader.pizzaSizeMap == null || DataLoader.pizzaToppingMap == null || DataLoader.pizzaSizeMap.size() == 0 || DataLoader.pizzaToppingMap.size() == 0) {
			dataLoader.run(null);
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
