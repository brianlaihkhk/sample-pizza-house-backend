package com.pizzahouse.test.suite;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pizzahouse.common.config.Default;
import com.pizzahouse.common.exception.DatabaseUnavailableException;
import com.pizzahouse.common.exception.JwtIssuerNotMatchException;
import com.pizzahouse.common.exception.JwtMessageExpiredException;
import com.pizzahouse.common.exception.OrderFullfillmentException;
import com.pizzahouse.common.exception.UnauthorizedException;
import com.pizzahouse.common.model.Response;
import com.pizzahouse.service.config.Connection;
import com.pizzahouse.service.controller.OrderService;
import com.pizzahouse.service.initialization.DataLoader;
import com.pizzahouse.service.model.Order;
import com.pizzahouse.test.data.DataLoaderTestData;
import com.pizzahouse.test.data.OrderTestData;
import com.pizzahouse.test.data.UserTestData;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/applicationContext.xml")
@TestPropertySource(properties = {
	    "pizzaServiceHost=http://localhost:8080/PizzaService/",
	    "orderConfirmationServiceHost=http://localhost:8080/OrderConfirmationService/",
	    "serverJwtSecretKey=bcbac5b821435e0be3e59d6abdac12a94c8248288a6ebd76a56a01fbca7c0cdf",
	    "serverIssuerName=cdc43c7e9089a41897b101de70f878bcc575c839f4ad057605a3335f6a601133",
	    "jwtTtlMilliseconds=10000",
	    "orderConfirmationServiceName=confirm"
	})
public class IntegrationTest {
	@Autowired
	protected OrderService orderService;
	@Autowired
	protected Logger logger;
	@Autowired
	protected Connection connection;
	
	
	/**
	 * Populate data to PizzaSizeMap and PizzaToppingMap
	 */
    @BeforeClass
    public static void instantiate() {
       	Default.hibernateConfigFilename = "hibernate.dev.cfg.xml";
    	DataLoader.pizzaSizeMap = DataLoaderTestData.generatePizzaSizeMapSet1();
    	DataLoader.pizzaToppingMap = DataLoaderTestData.generatePizzaToppingMapSet1();
    }

    /**
     * 
     * initialize data
     * @throws Exception 
     */
    @Test
    public void _00_initialize() throws Exception {
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
	 * @throws JwtIssuerNotMatchException 
	 * @throws JwtMessageExpiredException 
	 */
    @Test
    public void _01_orderPizzaSet1() throws UnauthorizedException, OrderFullfillmentException, JsonProcessingException, DatabaseUnavailableException, JwtMessageExpiredException, JwtIssuerNotMatchException {
    	Order order = OrderTestData.generateOrderSet1();
    	Response<String> response = orderService.submitOrder(UserTestData.jamesUserId, order);

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
	 * @throws JwtIssuerNotMatchException 
	 * @throws JwtMessageExpiredException 
	 */
    @Test
    public void _02_orderPizzaSet2() throws UnauthorizedException, OrderFullfillmentException, JsonProcessingException, DatabaseUnavailableException, JwtMessageExpiredException, JwtIssuerNotMatchException {
    	Order order = OrderTestData.generateOrderSet2();
    	Response<String> response = orderService.submitOrder(UserTestData.annaUserId, order);

       	assertEquals(true, response.isSuccess());

    }
}
