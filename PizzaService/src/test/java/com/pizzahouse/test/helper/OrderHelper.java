package com.pizzahouse.test.helper;

import java.util.List;

import com.pizzahouse.service.model.Order;
import com.pizzahouse.service.model.OrderDetail;

public class OrderHelper {

	/**
	 * Create Order Detail object
	 * @param pizzaTypeId Pizza Type ID
	 * @param pizzaSizeId Pizza Size ID
	 * @param pizzaToppingIdList List of Pizza Topping Ids
	 * @param quantity How many pizza with this configuration to buy
	 * @return OrderDetail object
	 */
 	public static OrderDetail createOrderDetail(int pizzaTypeId, int pizzaSizeId, List<Integer> pizzaToppingIdList, int quantity){
 		OrderDetail orderDetail = new OrderDetail();
 		orderDetail.setPizzaSizeId(pizzaSizeId);
 		orderDetail.setPizzaTypeId(pizzaTypeId);
 		orderDetail.setPizzaToppingIdList(pizzaToppingIdList);
 		orderDetail.setQuantity(quantity);

 		return orderDetail;
    }
 	
	/**
	 * Create Order object
	 * @param details List of orderDetail
	 * @return Order object
	 */
 	public static Order createOrder(List<OrderDetail> details){
 		Order order = new Order();
 		order.setDetails(details);
 		return order;
    }	
}
