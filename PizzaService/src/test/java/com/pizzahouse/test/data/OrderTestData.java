package com.pizzahouse.test.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pizzahouse.service.model.Order;
import com.pizzahouse.service.model.OrderDetail;
import com.pizzahouse.test.helper.OrderHelper;

public class OrderTestData {
	
	public static String neapolianPizzaUuid = "e676d1c5-7920-408a-a570-cd2b3d9ed24f";
	public static String chicagoPizzaUuid = "a08d319e-eba2-4d2c-bfbb-9b1e747f787e";
	public static String newYorkPizzaUuid = "3e4a0c24-b820-4a53-b379-5dacb38b439d";
	public static String californiaPizzaUuid = "3f59da26-fba9-4746-92f3-c6c003c59554";
	public static String roundRegularUuid = "0a06928c-92b9-4037-80a8-8022ca95e13b";
	public static String roundLargeUuid = "8d6ab16c-a2ab-46b3-8aa0-91674295afb7";
	public static String squareRegularUuid = "e423135c-b597-4ec9-a54a-cff57da39f38";
	public static String squareLargeUuid = "af9d2771-9dce-40a0-ac80-1adf19088017";
	public static String chickenUuid = "eca3dd33-0aab-4ce7-bb41-14bcc9212aaf";
	public static String eggUuid = "d53837f6-94d9-423c-9765-45e41073adb3";
	public static String goatCheeseUuid = "609d758d-11d0-4e9c-9643-45365a4d1817";
	public static String salmonUuid = "fb3a2777-af14-45b7-8765-a1e1d761e48e";
	public static String beefUuid = "3e85e4d5-6c0c-421c-8d52-387a8074c3ac";
	
	public static OrderDetail twoSquareLargeNeapolitanPizzaWithChickenEgg = OrderHelper.createOrderDetail(neapolianPizzaUuid, squareLargeUuid, new ArrayList<String>(Arrays.asList(chickenUuid, eggUuid)), 2);
	public static OrderDetail oneSquareRegularCaliforniaPizzaWithBeef = OrderHelper.createOrderDetail(californiaPizzaUuid, squareRegularUuid, new ArrayList<String>(Arrays.asList(beefUuid)), 1);
	public static OrderDetail oneRoundRegularChicagoPizzaWithChickenEggSalmon = OrderHelper.createOrderDetail(chicagoPizzaUuid, roundRegularUuid, new ArrayList<String>(Arrays.asList(chickenUuid, eggUuid, salmonUuid)), 1);
	public static OrderDetail twoRoundLargeNewYorkPizza = OrderHelper.createOrderDetail(newYorkPizzaUuid, roundLargeUuid, new ArrayList<String>(), 2);
	public static OrderDetail oneSquareRegularChicagoPizzaWithGoatCheese = OrderHelper.createOrderDetail(chicagoPizzaUuid, squareRegularUuid, new ArrayList<String>(Arrays.asList(goatCheeseUuid)), 1);

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
