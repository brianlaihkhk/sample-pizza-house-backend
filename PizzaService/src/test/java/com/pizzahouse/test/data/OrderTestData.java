package com.pizzahouse.test.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pizzahouse.service.model.Order;
import com.pizzahouse.service.model.OrderDetail;
import com.pizzahouse.test.helper.OrderHelper;

public class OrderTestData {
	
	public static OrderDetail twoSquareLargeNeapolitanPizzaWithChickenEgg = OrderHelper.createOrderDetail(1, 4, new ArrayList<Integer>(Arrays.asList(1, 2)), 2);
	public static OrderDetail oneSquareRegularCaliforniaPizzaWithBeef = OrderHelper.createOrderDetail(4, 3, new ArrayList<Integer>(Arrays.asList(5)), 1);
	public static OrderDetail oneRoundRegularChicagoPizzaWithChickenEggSalmon = OrderHelper.createOrderDetail(2, 1, new ArrayList<Integer>(Arrays.asList(1, 2, 4)), 1);
	public static OrderDetail twoRoundLargeNewYorkPizza = OrderHelper.createOrderDetail(3, 2, new ArrayList<Integer>(), 2);
	public static OrderDetail oneSquareRegularChicagoPizzaWithGoatCheese = OrderHelper.createOrderDetail(2, 3, new ArrayList<Integer>(3), 1);

	/**
	 * Generate Order Test Data Set 1
	 */
 	public static Order generateOrderSet1(){
 		Order order = new Order();
 		List<OrderDetail> details = new ArrayList<OrderDetail>();
 		details.add(twoSquareLargeNeapolitanPizzaWithChickenEgg);
 		details.add(twoRoundLargeNewYorkPizza);
 		
 		order.setDetails(details);
 		return order;
 	}

	/**
	 * Generate Order Test Data Set 2
	 */
 	public static Order generateOrderSet2(){
 		Order order = new Order();
 		List<OrderDetail> details = new ArrayList<OrderDetail>();
 		details.add(oneSquareRegularCaliforniaPizzaWithBeef);
 		details.add(twoRoundLargeNewYorkPizza);
 		details.add(oneRoundRegularChicagoPizzaWithChickenEggSalmon);

 		order.setDetails(details);
 		return order;
 	}
 	
	/**
	 * Generate Order Test Data Set 3
	 */
 	public static Order generateOrderSet3(){
 		Order order = new Order();
 		List<OrderDetail> details = new ArrayList<OrderDetail>();
 		details.add(oneSquareRegularChicagoPizzaWithGoatCheese);

 		order.setDetails(details);
 		return order;
 	}
}
