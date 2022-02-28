package com.pizzahouse.test.suite;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pizzahouse.common.exception.DatabaseUnavailableException;
import com.pizzahouse.common.exception.OrderFullfillmentException;
import com.pizzahouse.common.exception.UnauthorizedException;
import com.pizzahouse.common.model.Response;
import com.pizzahouse.service.controller.OrderService;
import com.pizzahouse.service.initialization.DataLoader;
import com.pizzahouse.service.model.Order;
import com.pizzahouse.test.data.DataLoaderTestData;
import com.pizzahouse.test.data.OrderTestData;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/applicationContext.xml")
public class IntegrationTest {
	@Autowired
	protected OrderService orderService;
	@Autowired
	protected Logger logger;
	
	/**
	 * Populate data to PizzaSizeMap and PizzaToppingMap
	 */
    @BeforeClass
    public static void instantiate() {
    	DataLoader.pizzaSizeMap = DataLoaderTestData.generatePizzaSizeMapSet1();
    	DataLoader.pizzaToppingMap = DataLoaderTestData.generatePizzaToppingMapSet1();
    }
    
	/**
	 *   Test the result generated from finalizedOrder
	 * 
	 *   Input :
	 *   -----------------------------------------
	 *   twoSquareLargeNeapolitanPizzaWithChickenEgg
	 *   twoRoundLargeNewYorkPizza
	 *   
	 *   Expected output :
	 *   -----------------------------------------
	 *   2 Square Large Neapolitan Pizza
	 *   2 Chicken Toppings on Neapolitan Pizza
	 *   2 Egg Toppings on Neapolitan Pizza
	 *   2 Round Large New York Pizza
	 *   
	 *   Total record : 4
	 *   Total amount : 2 * (149 + 15 + 15) + 2 * 129 = 616
	 * @throws JsonProcessingException 
	 * @throws DatabaseUnavailableException 
	 */
    @Test
    public void _01_orderPizzaSet1() throws UnauthorizedException, OrderFullfillmentException, JsonProcessingException, DatabaseUnavailableException {
    	Order order = OrderTestData.generateOrderSet1();
    	Response<String> response = orderService.submitOrder(1, order);

       	assertEquals(true, response.isSuccess());
    }
    
	/**
	 *   Test the result generated from finalizedOrder
	 * 
	 *   Input :
	 *   -----------------------------------------
	 *    OneSquareRegularCaliforniaPizzaWithBeef
	 *    twoRoundLargeNewYorkPizza
	 *    OneRoundRegularChicagoPizzaWithChickenEggSalmon
	 *    
	 *    Expected output :
	 *   --------------------------------------
	 *   1 Square Regular California Pizza
	 *   1 Beef topping on California Pizza
	 *   2 Egg Toppings on Neapolitan Pizza
	 *   1 Round Regular Chicago Pizza
	 *   1 Chicken topping on Chicago Pizza
	 *   1 Egg topping on Chicago Pizza
	 *   1 Salmon topping on Chicago Pizza
	 *   
	 *   Total record : 7
	 *   Total amount : 119 + 20 + 129 + 129 + 99 + 15 + 15 + 25 = 551
	 * @throws JsonProcessingException 
	 * @throws DatabaseUnavailableException 
	 */
    @Test
    public void _02_orderPizzaSet2() throws UnauthorizedException, OrderFullfillmentException, JsonProcessingException, DatabaseUnavailableException {
    	Order order = OrderTestData.generateOrderSet2();
    	Response<String> response = orderService.submitOrder(2, order);

       	assertEquals(true, response.isSuccess());

    }
}
